module counter (clk,rst,enable,out,r_out);



input clk,rst,enable;

output reg [15:0] out;
output reg r_out;
always@(posedge clk) begin

if (rst) begin
 out <=0;
 r_out <= 1;
end else begin
  if (out == 10) begin
   r_out<=0;
   out <=0;
  end
  else begin
  if (enable) begin
    out <= out + 1;
    r_out <= 1;
  end
 end 
end
end





endmodule
