package cn.tuyucheng.taketoday.ejb.wildfly;

import javax.ejb.Stateless;

@Stateless
public class TextProcessorBean implements TextProcessorRemote {
	public String processText(String text) {
		return text.toUpperCase();
	}
}
