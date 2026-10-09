package pro1;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "project1")
public class DataDocument {
    @Id
    private String id;

    @Field("Col-7")
    private double col7;

    public double getCol7() {
        return col7;
    }

    public void setCol7(double col7) {
        this.col7 = col7;
    }
}