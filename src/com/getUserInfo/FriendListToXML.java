package com.getUserInfo;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class FriendListToXML {

	public FriendListToXML() {
		// TODO Auto-generated constructor stub
	}

	public String ToXml(List<UserInfo> friendList, String userName) {
		Document document = null;
		String fileName = "E:/JAVA/Andriod/MyEclipseWorkShop/NoticeServer/WebRoot/"+userName+"'friend.xml";
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
			Element root = document.createElement("friends");
			document.appendChild(root);
			
			int FriendNumber = friendList.size();
			Element element[] = new Element[FriendNumber];
			Element idElement[] = new Element[FriendNumber];
			Element UsernameElement[] = new Element[FriendNumber];
			Element IPElement[] = new Element[FriendNumber];
			Element Local_longitudeElement[] = new Element[FriendNumber];
			Element Local_latitudeElement[] = new Element[FriendNumber];
			Element NameElement[] = new Element[FriendNumber];
			Element PhoneElement[] = new Element[FriendNumber];
			Element SexElement[] = new Element[FriendNumber];
			
			for(int i = 0;i<FriendNumber;i++) {
				element[i] = document.createElement("friend");
				
				idElement[i] = document.createElement("ID");
				idElement[i].appendChild(document.createTextNode(friendList.get(i).getID().toString()));
				element[i].appendChild(idElement[i]);
				
				UsernameElement[i] = document.createElement("username");
				UsernameElement[i].appendChild(document.createTextNode(friendList.get(i).getUsername()));
				element[i].appendChild(UsernameElement[i]);
				
				IPElement[i] = document.createElement("IP");
				IPElement[i].appendChild(document.createTextNode(friendList.get(i).getIP()));
				element[i].appendChild(IPElement[i]);
				
				Local_longitudeElement[i] = document.createElement("Local_longitude");
				Local_longitudeElement[i].appendChild(document.createTextNode(friendList.get(i).getLocal_longitude().toString()));
				element[i].appendChild(Local_longitudeElement[i]);
				
				Local_latitudeElement[i] = document.createElement("Local_latitude");
				Local_latitudeElement[i].appendChild(document.createTextNode(friendList.get(i).getLocal_latitude().toString()));
				element[i].appendChild(Local_latitudeElement[i]);
				
				NameElement[i] = document.createElement("Name");
				NameElement[i].appendChild(document.createTextNode(friendList.get(i).getName()));
				element[i].appendChild(NameElement[i]);
				
				PhoneElement[i] = document.createElement("Phone");
				PhoneElement[i].appendChild(document.createTextNode(friendList.get(i).getPhone()));
				element[i].appendChild(PhoneElement[i]);
				
				SexElement[i] = document.createElement("Sex");
				SexElement[i].appendChild(document.createTextNode(friendList.get(i).getSex()));
				element[i].appendChild(SexElement[i]);
				
				root.appendChild(element[i]);
			}
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(document);
			transformer.setOutputProperty(OutputKeys.ENCODING, "GB2312");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
			StreamResult result = new StreamResult(pw);
			transformer.transform(source, result);
			System.out.println("创建xml成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "ok";
	}

}
