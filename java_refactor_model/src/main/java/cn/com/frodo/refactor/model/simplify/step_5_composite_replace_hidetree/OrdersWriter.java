package cn.com.frodo.refactor.model.simplify.step_5_composite_replace_hidetree;

/**
 * 
 * @author frodoking <br>
 *         有时候虽然数据或代码没有被显示地构造成树，但可能是用树表示的，这时候它就形成了隐含树
 */
public class OrdersWriter {
	private Orders orders;

	public OrdersWriter(Orders orders) {
		this.orders = orders;
	}

	public String getContents() {
		StringBuffer xml = new StringBuffer();
		writeOrderTo(xml);
		return xml.toString();
	}

	private void writeOrderTo(StringBuffer xml) {
		TagNode ordersTag = new TagNode("orders");
		for (int i = 0; i < orders.getOrderCount(); i++) {
			Order order = orders.getOrder(i);
			TagNode orderTag = new TagNode("order");
			orderTag.addAttribute("id", order.getOrderId());

			writeProductsTo(xml, order);
			ordersTag.add(orderTag);
		}
		xml.append(ordersTag.toString());
	}

	private void writeProductsTo(StringBuffer xml, Order order) {
		for (int i = 0; i < order.getProductCount(); i++) {
			Product product = order.getProduct(i);
			TagNode productTag = new TagNode("product");
			productTag.addAttribute("id", product.getId());
			productTag.addAttribute("color", colorFor(product));

			if (product.getSize() != ProductSize.NOT_APPLICABLE) {
				productTag.addAttribute("size", sizeFor(product));
			}

			writePriceTo(productTag, product);
			productTag.addValue(product.getName());
			xml.append(productTag.toString());
		}
	}

	private void writePriceTo(TagNode productTag, Product product) {
		TagNode priceTag = new TagNode("price");
		priceTag.addAttribute("currency", currencyFor(product));
		priceTag.addValue(priceFor(product));
		productTag.add(priceTag);
	}

	private String priceFor(Product product) {
		return null;
	}

	private String currencyFor(Product product) {
		return null;
	}

	private String sizeFor(Product product) {
		return null;
	}

	private String colorFor(Product product) {
		return null;
	}
}
