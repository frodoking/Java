package cn.com.frodo.refactor.model.generalization.step_7_interpreter_replace_hide;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 类中的许多方法组合成了一种隐式语言的元素.采用解释器模式，将针对不同查询或计算方式通过不同的组合完成.
 *
 * @author frodoking
 */
public class ProductFinder {

    public List<Product> belowPrice(float price) {
        BelowPriceSpec spec = new BelowPriceSpec(price);
        return selectBy(spec);
    }

    private List<Product> selectBy(Spec spec) {
        List<Product> foundProducts = new ArrayList<Product>();
        Iterator<Product> products = foundProducts.iterator();
        while (products.hasNext()) {
            Product product = products.next();
            if (spec.isSatisfiedBy(product)) {
                foundProducts.add(product);
            }
        }

        return foundProducts;
    }

    // ****************下面的全是对应各种组合方式*****************
    public List<Product> byColor(Color colorOfProductToFind) {
        ColorSpec spec = new ColorSpec(colorOfProductToFind);
        return selectBy(spec);
    }

    public List<Product> byColorAndBelowPrice(Color color, float price) {
        ColorSpec colorSpec = new ColorSpec(color);
        BelowPriceSpec priceSpec = new BelowPriceSpec(price);

        AndSpec spec = new AndSpec(colorSpec, priceSpec);

        return selectBy(spec);
    }

    public List<Product> belowPriceAvoidingAColor(float price, Color color) {
        BelowPriceSpec belowPriceSpec = new BelowPriceSpec(price);
        NotSpec notSpec = new NotSpec(new ColorSpec(color));

        AndSpec spec = new AndSpec(belowPriceSpec, notSpec);

        return selectBy(spec);
    }
}
