package com.notice.DataToXml;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.notice.GetData.Notice;

public class NoticeToXml {

	public NoticeToXml() {
		// TODO Auto-generated constructor stub
	}

	public String ToXml(List<Notice> noticelist) {
		Document document = null;
		String fileName = "E:/JAVA/Andriod/MyEclipseWorkShop/NoticeServer/WebRoot/Notice.xml";
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
			Element root = document.createElement("notices");
			document.appendChild(root);

			// 获得通知的数量
			int NoticeNumber = noticelist.size();
			Element element[] = new Element[NoticeNumber];
			Element idElement[] = new Element[NoticeNumber];
			Element EmphasisElement[] = new Element[NoticeNumber];
			Element TimeElement[] = new Element[NoticeNumber];
			Element PlaceElement[] = new Element[NoticeNumber];
			Element DetailsElement[] = new Element[NoticeNumber];
			Element SenderElement[] = new Element[NoticeNumber];

			for (int i = 0; i < NoticeNumber; i++) {
				element[i] = document.createElement("notice");
				idElement[i] = document.createElement("Id");
				idElement[i].appendChild(document.createTextNode(noticelist
						.get(i).getId().toString()));
				element[i].appendChild(idElement[i]);

				EmphasisElement[i] = document.createElement("Emphasis");
				EmphasisElement[i].appendChild(document
						.createTextNode(noticelist.get(i).getEmphasis()));
				element[i].appendChild(EmphasisElement[i]);

				TimeElement[i] = document.createElement("NoticeTime");
				TimeElement[i].appendChild(document.createTextNode(noticelist
						.get(i).getNoticeTime()));
				element[i].appendChild(TimeElement[i]);

				PlaceElement[i] = document.createElement("Place");
				PlaceElement[i].appendChild(document.createTextNode(noticelist
						.get(i).getPlace()));
				element[i].appendChild(PlaceElement[i]);

				DetailsElement[i] = document.createElement("Details");
				DetailsElement[i].appendChild(document
						.createTextNode(noticelist.get(i).getDetails()));
				element[i].appendChild(DetailsElement[i]);
				//root.appendChild(element[i]);

				SenderElement[i] = document.createElement("Sender");
				SenderElement[i].appendChild(document
						.createTextNode(noticelist.get(i).getSender()));
				element[i].appendChild(SenderElement[i]);
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
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ok";
	}
}
