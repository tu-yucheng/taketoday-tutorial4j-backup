package cn.tuyucheng.taketoday.ejb.wildfly;

import javax.ejb.Remote;

@Remote
public interface TextProcessorRemote {

	String processText(String text);
}