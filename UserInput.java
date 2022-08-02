package budget;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserInput {
    private int integerInput;
    private float floatInput;
    private String strInput;

    private final static Scanner SCANNER = new Scanner(System.in);

    public UserInput() {
        this.integerInput = -1;
        this.floatInput = 0;
        this.strInput = "";
    }

    public void setIntegerInput() {
        this.integerInput = SCANNER.nextInt();
    }

    public boolean isExit() {
        return this.integerInput == 0;
    }

    public int getIntegerInput() {
        return this.integerInput;
    }

    public void setFloatInput() {
        BigDecimal input = new BigDecimal(SCANNER.nextFloat());
        this.floatInput = input.floatValue();
    }

    public float getFloatInput() {
        return this.floatInput;
    }

    public void setStrInput() {
        this.strInput = SCANNER.nextLine();
    }

    public String getStrInput() {
        return this.strInput;
    }



}

