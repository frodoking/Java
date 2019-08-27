package cn.com.frodo.refactor.model.generalization.step_3_composite_replace_morebranch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 如果一个类含有两个几乎一样的方法，唯一的区别就是一个用来处理单一对象，
 * 一个用来处理对象的集合，那么一/多之分就出现了。
 * [容易引发重复代码，不一致的客户代码，结果的合并]
 * @author frodoking
 *
 */
public class ProductRepository {
	private List<Product> products = new ArrayList<Product>();

	public Iterator<Product> iterator() {
		return products.iterator();
	};

	public List<Product> selectBy(Spec spec) {
		Iterator<Product> productsTmp = iterator();
		while (productsTmp.hasNext()) {
			Product product = productsTmp.next();
			if (spec.isSatisfiedBy(product)) {
				products.add(product);
			}
		}

		return products;
	}

//	public List<Product> selectBy(List<Spec> specs) {
//		return selectBy(new CompositeSpec());
//	}
}
