
`include "firFilter.v"
`include "counter.v"


module topFPGA(

input clk,
input rst,
input en,
output LEDG,
output [6:0] HEX0,
output [6:0] HEX1
);
wire r_out;
wire newclk;
counter counter(.clk(clk),.rst(rst),.enable(en),.out(cont_in),.r_out(r_out));
wire [15:0] cont_in;
wire [15:0] data_out;
firFilter fir4_(
  .clk(clk),
  .rst(rst),
  .enable(en),
  .rIn1(r_out),
  .dataIn1(cont_in)
 );

endmodule
