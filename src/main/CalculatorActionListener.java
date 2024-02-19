package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import ui.CalculatorUI;

public class CalculatorActionListener implements ActionListener {
	
	private CalculatorUI ui;
	
	// store operator
	public String operator = "";
	
	// store operands
	public String s1 = "", s2 = "";
	
	public CalculatorActionListener(CalculatorUI calculatorFrame) {
		this.ui = calculatorFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String s = e.getActionCommand();
		
		//ui.inputTrackingField.setText(ui.inputTrackingField.getText() + (s.equals("=") ? "" : s));
		ui.inputTrackingField.setText(ui.inputTrackingField.getText() + s);
	
		// if the value is a number
		if (s.charAt(0) >= '0' && s.charAt(0) <= '9' || s.equals(".")) {
			// if operand is present then add to second no
			if (!operator.equals("")) {
				s2 += s;
			} else {
				s1 += s;
			}
		
//			ui.textFieldInput.setText(s1 + operator + s2);
		} else if (s.equals("=")) {
			// perform calculations
			double result = this.calculator();

			s1 = String.valueOf(result);
			s2 = "";
			operator = "";
			
			ui.inputTrackingField.setText(s1);
			ui.textFieldInput.setText(s1 + operator + s2);
		}
		else if(s.equals("C")) {
			// reset operators and operands
			s1 = "";
			s2 = "";
			operator = "";
			
			ui.inputTrackingField.setText("");
			ui.textFieldInput.setText("0");
		}
		else {
			// if the button clicked represents an operator and two operands are already present
			// perform the calculation for existing operator and operands
			// then, update the display the result and reset the operator and second operand for the next calculation
			if(!operator.equals("") || !s2.equals("")) {
				double result = calculator();
			
				s1 = String.valueOf(result);
				s2 = "";
			}
			
			// place the operator
			operator = s;
			
//			ui.textFieldInput.setText(s1);
		}
	}

	private double calculator() {
		double result = 0;
		if (operator.equals("+")) {
			result = Double.parseDouble(s1) + Double.parseDouble(s2);
		} else if (operator.equals("-")) {
			result = Double.parseDouble(s1) - Double.parseDouble(s2);
		} else if (operator.equals("*")) {
			result = Double.parseDouble(s1) * Double.parseDouble(s2);
		} else if (operator.equals("/")) {
			result = Double.parseDouble(s1) / Double.parseDouble(s2);
		}
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		return Double.parseDouble(decimalFormat.format(result));
	}
}
