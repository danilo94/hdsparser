

module MUL #
(
  parameter N = 16
)
(
  input CLK,
  input RST,
  input EN,
  input R_IN1,
  input [N-1:0] D_IN1,
  input R_IN2,
  input [N-1:0] D_IN2,
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
          if(R_IN1 & R_IN2) begin
            if(D_IN2 == 0) begin
              D_OUT_REG <= 0;
              R_OUT_REG <= R_IN1;
            end else begin
              D_OUT_REG <= D_IN1 * D_IN2;
              R_OUT_REG <= R_IN1;
            end
          end else begin
            R_OUT_REG <= 0;
          end
        end 
      end 
    end
  end


endmodule

