package cn.com.frodo.refactor.model.generalization.step_7_interpreter_replace_hide;

import java.awt.*;

public class ColorSpec extends Spec {
    private Color colorOfProductToFind;

    public ColorSpec(Color colorOfProductToFind) {
        this.colorOfProductToFind = colorOfProductToFind;
    }

    public Color getColorOfProductToFind() {
        return colorOfProductToFind;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getColor().equals(getColorOfProductToFind());
    }

}
