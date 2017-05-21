

module ANDI #
(
  parameter N = 16,
  parameter I = 1
)
(
  input CLK,
  input RST,
  input EN,
  input R_IN,
  input [N-1:0] D_IN,
  output R_OUT,
  output [N-1:0] D_OUT
);

  reg [N-1:0] D_OUT_REG;
  reg R_OUT_REG;
  assign D_OUT = D_OUT_REG;
  assign R_OUT = R_OUT_REG;

  always @(posedge CLK) begin
    if(RST) begin
      R_OUT_REG <= 0;
      D_OUT_REG <= 0;
    end else begin
      if(CLK) begin
        if(EN) begin
          if(R_IN) begin
            D_OUT_REG <= D_IN & I;
            R_OUT_REG <= R_IN;
          end else begin
            R_OUT_REG <= 0;
          end
        end 
      end 
    end
  end


endmodule

