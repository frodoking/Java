package cn.com.frodo.refactor.model.create.step_5_builder_package_composite;

import junit.framework.TestCase;

public class TagBuilderTest extends TestCase {

	public void testBuilderOneNode() {
		String expectedXML = 
				"<flavors>" + 
				"<flavor>"+
				"<requirements>"+
				"<requirement/>"+
				"</requirements>"+
				"</flavor>"+
				"</flavors>";

		TagBuilder builder = new TagBuilder("flavors");
		builder.addChild("flavor");
		builder.addChild("requirements");
		builder.addChild("requirement");
		String actualXML = builder.toXML();

		assertEquals(expectedXML, actualXML);
	}
	
	public void testParent(){
		TagNode root = new TagNode("root");
		assertNull(root.getParent());
	}
	
	public void testBuildSibling(){
		String expectedXML = 
				"<flavors>" + 
				"<flavor1/>"+
				"<flavor2/>"+
				"</flavors>";

		TagBuilder builder = new TagBuilder("flavors");
		builder.addChild("flavor1");
		builder.addSibling("flavor2");
		String actualXML = builder.toXML();

		assertEquals(expectedXML, actualXML);
	}
	
	public void testRepteatingChildrenAndGrandchildren(){
		String expectedXML = 
				"<flavors>" + 
		
				"<flavor>"+
				"<requirements>"+
				"<requirement/>"+
				"</requirements>"+
				"</flavor>"+
				
				"<flavor>"+
				"<requirements>"+
				"<requirement/>"+
				"</requirements>"+
				"</flavor>"+
				
				"</flavors>";
		
		TagBuilder builder = new TagBuilder("flavors");
		for (int i = 0; i < 2; i++) {
			builder.addToParent("flavors", "flavor");
			builder.addChild("requirements");
			builder.addChild("requirement");
		}
		
		String actualXML = builder.toXML();
		assertEquals(expectedXML, actualXML);
	}
	
	public void testParentNameNotFound(){
		TagBuilder builder = new TagBuilder("flavors");
		for (int i = 0; i < 2; i++) {
			//FIXME error
			builder.addToParent("favors", "flavor");
			builder.addChild("requirements");
			builder.addChild("requirement");
			fail("should not allow adding to parent that doesn't exist.");
		}
	}
	
}
