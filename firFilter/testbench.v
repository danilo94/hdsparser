`include "topFPGA.v"

module tb();

reg clk;
reg rst;

topFPGA fpga1(.clk(clk),.rst(rst),.en(1'b1));


always
#1 clk = ~clk;


initial begin
clk =0;
rst =1;
#3 rst = 0;

end

initial begin
$dumpfile("out.vcd");
$dumpvars();
end


initial
#1000 $finish;


endmodule
