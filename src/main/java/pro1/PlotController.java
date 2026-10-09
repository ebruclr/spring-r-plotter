package pro1;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.function.Function;

@Controller
public class PlotController {

    @Autowired
    private DataRepository dataRepository;

    @Value("classpath:plot.R")
    private Resource rSource;

    private int currentIndex = 0;
    private final int MAX_COUNT = 100; // Limit
    private Function<DataHolder, String> plotFunction;
    private String lastSvg = "<svg></svg>";

    @PostConstruct
    public void init() throws IOException {
        Context graalContext = Context.newBuilder().allowAllAccess(true).build();
        Source source = Source.newBuilder("R", rSource.getURL()).build();
        this.plotFunction = graalContext.eval(source).as(Function.class);
    }

    @RequestMapping(value = "/plot", produces = "image/svg+xml")
    public ResponseEntity<String> load() {
        HttpHeaders responseHeaders = new HttpHeaders();

        if (currentIndex >= MAX_COUNT) {
            currentIndex = 0;
        }

        Page<DataDocument> page = dataRepository.findAll(PageRequest.of(currentIndex, 1));

        Double currentValue = null;

        if (page.hasContent()) {
            currentValue = page.getContent().get(0).getCol7();
        } else {
            currentValue = Math.sin(currentIndex / 10.0) * 10;
        }

        responseHeaders.set("Refresh", "1");

        synchronized (plotFunction) {
            lastSvg = plotFunction.apply(new DataHolder(currentValue));
        }

        currentIndex++;

        return new ResponseEntity<>(lastSvg, responseHeaders, HttpStatus.OK);
    }
}