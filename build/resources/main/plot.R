library(lattice)
data <- numeric(100)

function(dataHolder) {
    svg()
    data <<- c(data[2:100], dataHolder$value)

    plot <- xyplot(dataValue ~ time,
                   data = data.frame(dataValue = data, time = 1:100),
                   main = 'MongoDB Col7 Data Plot',
                   xlab="Time (s)",
                   ylab = "Col7 Value",
                   xlim = c(1,100),
                   type = c('l', 'g'),
                   col.line = "#654321")
    print(plot)
    svg.off()
}