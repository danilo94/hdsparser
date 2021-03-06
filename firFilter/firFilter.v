`include "REG.v"
`include "MULI.v"
`include "ADD.v"


module firFilter(dataOut1,r_out,dataIn1,rIn1, clk, rst, enable);
input clk,rst,enable;
output [16-1:0]dataOut1;
output r_out;
input [16-1:0]dataIn1;
input rIn1;
//Fim da criação da interface com o mundo exterior
wire [1-1:0]en_wire;
wire [16-1:0]n9;
wire [1-1:0]n8;
wire [1-1:0]n7;
wire [16-1:0]n6;
wire [16-1:0]n5;
wire [1-1:0]n4;
wire [1-1:0]n3;
wire [16-1:0]n17;
wire [16-1:0]n2;
wire [1-1:0]n16;
wire [1-1:0]n1;
wire [1-1:0]n15;
wire [16-1:0]n0;
wire [16-1:0]n14;
wire [1-1:0]reset_wire;
wire [16-1:0]n13;
wire [1-1:0]n12;
wire [1-1:0]n11;
wire [16-1:0]n10;
wire [1-1:0]clock_wire;
assign en_wire= enable;
assign reset_wire= rst;
assign clock_wire= clk;
assign dataOut1=n17;
assign r_out = n15;
assign n14=dataIn1;
assign n16=rIn1;
REG #(.N(16),.I(1.0E-8)) REG0(.CLK(clock_wire),.RST(reset_wire),.EN(en_wire),.R_IN(n1),.D_IN(n0),.R_OUT(n3),.D_OUT(n2));
MULI #(.N(16),.I(4)) MULI1(.CLK(clock_wire),.RST(reset_wire),.EN(en_wire),.R_IN(n16),.D_IN(n14),.R_OUT(n1),.D_OUT(n0));
MULI #(.N(16),.I(3)) MULI2(.CLK(clock_wire),.RST(reset_wire),.EN(en_wire),.R_IN(n16),.D_IN(n14),.R_OUT(n4),.D_OUT(n5));
MULI #(.N(16),.I(2)) MULI3(.CLK(clock_wire),.RST(reset_wire),.EN(en_wire),.R_IN(n16),.D_IN(n14),.R_OUT(n8),.D_OUT(n9));
MULI #(.N(16),.I(1)) MULI4(.CLK(clock_wire),.RST(reset_wire),.EN(en_wire),.R_IN(n16),.D_IN(n14),.R_OUT(n12),.D_OUT(n13));
ADD #(.N(16)) ADD0(.CLK(clock_wire),.RST(reset_wire),.EN(en_wire),.R_IN1(n11),.D_IN1(n10),.R_OUT(n15),.D_OUT(n17),.D_IN2(n13),.R_IN2(n12));
ADD #(.N(16)) ADD1(.CLK(clock_wire),.RST(reset_wire),.EN(en_wire),.R_IN1(n7),.D_IN1(n6),.R_OUT(n11),.D_OUT(n10),.D_IN2(n9),.R_IN2(n8));
ADD #(.N(16)) ADD2(.CLK(clock_wire),.RST(reset_wire),.EN(en_wire),.R_IN1(n3),.D_IN1(n2),.R_OUT(n7),.D_OUT(n6),.D_IN2(n5),.R_IN2(n4));
endmodule
