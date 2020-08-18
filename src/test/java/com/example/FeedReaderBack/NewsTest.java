package com.example.FeedReaderBack;

import antlr.ASTNULLType;
import com.example.FeedReaderBack.model.BroadcastStationItem;
import com.example.FeedReaderBack.model.SbsNewsItem;
import com.example.FeedReaderBack.service.SetNews;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

@SpringBootTest
public class NewsTest {

    @Test
    public void xmltest() throws ParserConfigurationException, IOException, SAXException {
        SetNews setNews = new SetNews();

        String xmlfeed = "<item>\n" +
                "\t\t\t\n" +
                "\t\t\t\n" +
                "\t\t\t<title><![CDATA[靑, 전광훈 확진에 \"우려가 현실이 됐다…비협조 결과\"]]></title>\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\n" +
                "\t\t\t\n" +
                "\t\t\t<link><![CDATA[https://news.sbs.co.kr/news/endPage.do?news_id=N1005936197&plink=RSSLINK&cooper=RSSREADER]]></link>\n" +
                "\t\t\t\n" +
                "\t\t\t<guid>https://news.sbs.co.kr/news/endPage.do?news_id=N1005936197</guid>\n" +
                "\t\t\t\n" +
                "\t\t\t<pubDate>Tue, 18 Aug 2020 16:30:00 +0900</pubDate>\n" +
                "\t\t\t\n" +
                "\t\t\t<author>sbsnewsmedia@sbs.co.kr(SBS 뉴스)</author>\n" +
                "\t\t\t\n" +
                "\t\t\t<description><![CDATA[청와대는 18일 광복절 광화문 집회 참가를 독려한 전광훈 사랑제일교회 목사가 코로나바이러스감염증-19 확진 판정을 받은 데 대해 &#34;우려가 현실이 됐다&#34;고 밝혔다. 청와대 핵심 관계자는 이날 기자들과 만나 이같이 말하고 &#34;방역당국의 경고에 비협조로 일관해 왔는데, 8·15 광화문 집회에 참석한 분들은 전원 검사를 받아야 한다는 사실을 실증했다&#34;고 밝혔다.]]></description>\n" +
                "\t\t\t\n" +
                "\t\t\t\n" +
                "\t\t\t\n" +
                "\t\t\t\t<category domain=\"https://news.sbs.co.kr/news/newsSection.do?sectionType=01\"><![CDATA[정치]]></category>\n" +
                "\t\t\t\t<category domain=\"https://news.sbs.co.kr/news/newsSection.do?sectionType=01\"><![CDATA[POLITICS]]></category>\n" +
                "\t\t\t\t<category domain=\"https://news.sbs.co.kr/\"><![CDATA[SBS 뉴스]]></category>\n" +
                "\t\t\t\t<category domain=\"https://news.sbs.co.kr/\"><![CDATA[SBS 뉴스]]></category>\n" +
                "\t\t\t\t<category domain=\"https://news.sbs.co.kr/\"><![CDATA[SBS NEWS]]></category>\n" +
                "\t\t\t\t<category domain=\"https://news.sbs.co.kr/\"><![CDATA[NEWS]]></category>\n" +
                "\t\t\t\t<category domain=\"https://news.sbs.co.kr/\"><![CDATA[뉴스]]></category>\t\t\n" +
                "\t\t\t\t<category domain=\"https://www.sbs.co.kr/\"><![CDATA[SBS]]></category>\n" +
                "\t\t\t\t<category domain=\"https://www.sbs.co.kr/\"><![CDATA[서울방송]]></category>\n" +
                "\t\t\t\t<category domain=\"https://www.sbs.co.kr/\"><![CDATA[Seoul Broadcast Station]]></category>\n" +
                "\t\t\t\t<category><![CDATA[일반기사]]></category>\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t\t\t<category domain=\"https://news.sbs.co.kr/news/keywordList.do?keyword=%EC%9A%B0%EB%A0%A4%EA%B0%80+%ED%98%84%EC%8B%A4%EC%9D%B4+%EB%90%90%EB%8B%A4\"><![CDATA[우려가 현실이 됐다]]></category>\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t\t\t<category domain=\"https://news.sbs.co.kr/news/keywordList.do?keyword=%ED%99%95%EC%A7%84\"><![CDATA[확진]]></category>\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t\t\t<category domain=\"https://news.sbs.co.kr/news/keywordList.do?keyword=%EC%A0%84%EA%B4%91%ED%9B%88\"><![CDATA[전광훈]]></category>\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t\t\t<category domain=\"https://news.sbs.co.kr/news/keywordList.do?keyword=%EC%BD%94%EB%A1%9C%EB%82%9819\"><![CDATA[코로나19]]></category>\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t\t\t<category domain=\"https://news.sbs.co.kr/news/keywordList.do?keyword=%EB%B9%84%ED%98%91%EC%A1%B0\"><![CDATA[비협조]]></category>\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\n" +
                "\t\t\t\n" +
                "\t\t\t<category domain=\"https://news.sbs.co.kr/news/newsHotIssueList.do?tagId=10000050973\"><![CDATA[코로나19 현황]]></category>\n" +
                "\t\t\t\n" +
                "\t\t\t\n" +
                "\t\t\t<!--  -->\n" +
                "\t\t\t\n" +
                "\t\t\t\n" +
                "\t\t\t\t<!--I-->\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t<enclosure url=\"https://img.sbs.co.kr/newimg/news/20200724/201453809.jpg\"  type=\"image/jpeg\" length=\"216019\"/>\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t<content:encoded><![CDATA[\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t<!-- tracking Pixel -->ⓒ SBS &amp; SBS Digital News Lab / RSS 피드는 개인 리더 이용 목적으로 허용 되어 있습니다. 피드를 이용한 게시 등의 무단 복제는 금지 되어 있습니다.<img src=\"https://news.sbs.co.kr/news/tracking_RSS.do?news_id=N1005936197&amp;cooper=RSSREADER\" alt='track pixel'><!-- //tracking Pixel -->\n" +
                "\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t<p><a href=\"https://news.sbs.co.kr/news/appinstall.do?plink=APPDOWN&amp;cooper=RSSREADER\">▶SBS 뉴스 앱 다운로드</a></p><p>ⓒ SBS &amp; SBS Digital News Lab  : 무단복제 및 재배포 금지</p>\n" +
                "\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t<img src=\"http://img.sbs.co.kr/newimg/news/20200724/201453809.jpg\" width=\"700\">\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\n" +
                "<!--0--><p class='change'>청와대는 18일 광복절 광화문 집회 참가를 독려한 전광훈 사랑제일교회 목사가 코로나바이러스감염증-19(코로나19) 확진 판정을 받은 데 대해 \"우려가 현실이 됐다\"고 밝혔다.</p>\n" +
                "<!--1--><p class='change'> 청와대 핵심 관계자는 이날 기자들과 만나 이같이 말하고 \"방역당국의 경고에 비협조로 일관해 왔는데, 8·15 광화문 집회에 참석한 분들은 전원 검사를 받아야 한다는 사실을 실증했다\"고 밝혔다.</p>\n" +
                "<!--2--><p class='change'> 그러면서 \"언론보도에 의하면 신천지 초기보다 코로나19 확산 속도가 빠른 것 아니냐\"며 \"방역당국에 비협조한 결과가 그간 국민의 노력에 찬물을 끼얹는 결과로 나타나는 것\"이라고 개탄했다.</p>\n" +
                "<!--3--><p class='change'> 나아가 청와대는 사랑제일교회를 비롯한 일부 교회들을 중심으로 코로나19가 확산하는 데 강한 우려를 표하면서 강력한 방역조치를 방해하는 행위를 엄단하겠다는 입장을 밝혔다.</p>\n" +
                "<!--4--><p class='change'> 이 관계자는 광화문 집회 참석자 전원이 자발적으로 검사를 받아야 한다는 점을 거듭 강조하면서 \"검사를 받는 것은 본인뿐 아니라 국민의 안전을 위한 것\"이라고 말했다.</p>\n" +
                "<!--5--><p class='change'> 그는 코로나19가 서울, 경기뿐 아니라 인천, 충남, 대구, 전북, 강원 등 전국적으로 퍼지고 있다면서 \"지금은 상당히 엄중한 상황이자 방역의 중대 기로\"라고 했다.</p>\n" +
                "<!--6--><p class='change'> 이어 \"정부는 앞으로 더 강력한 방역 조치를 취할 것이고, 방역을 방해하는 일체 행위에 대해서는 국민 안전 보호와 법치 확립 차원에서 엄단할 것\"이라고 강조했다.</p>\n" +
                "<!--7--><p class='change'> (연합뉴스/사진=연합뉴스TV 제공, 연합뉴스)</p>\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t<p><a href=\"https://news.sbs.co.kr/news/endPage.do?news_id=N1005936197&amp;plink=ORI&amp;cooper=RSSREADER\">▶SBS 뉴스 원문 기사 보기</a></p>\n" +
                "\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t<p><a href=\"https://news.sbs.co.kr/news/appinstall.do?plink=APPDOWN&amp;cooper=RSSREADER\">▶SBS 뉴스 앱 다운로드</a></p><p>ⓒ SBS &amp; SBS Digital News Lab  : 무단복제 및 재배포 금지</p>\n" +
                "\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t]]></content:encoded>\n" +
                "\t\t\t\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t<media:thumbnail url=\"http://img.sbs.co.kr/newimg/news/20200724/201453809.jpg\"/>\n" +
                "\t\t\t\t<media:content url=\"https://img.sbs.co.kr/newimg/news/20200724/201453809.jpg\" medium=\"image\">\n" +
                "\t\t\t\t\t<media:credit>\n" +
                "\t\t\t\t\t<![CDATA[ SBS 뉴스]]>\n" +
                "\t\t\t\t\t</media:credit>\n" +
                "\t\t\t\t\t<media:description>\n" +
                "\t\t\t\t\t<![CDATA[ 기사 썸네일 ]]>\n" +
                "\t\t\t\t\t</media:description>\n" +
                "\t\t\t\t\t<media:title>\n" +
                "\t\t\t\t\t<![CDATA[靑, 전광훈 확진에 \"우려가 현실이 됐다…비협조 결과\"]]>\n" +
                "\t\t\t\t\t</media:title>\n" +
                "\t\t\t\t</media:content>\n" +
                "\t\t\t\t\n" +
                "\n" +
                "\t\t</item>";

        InputSource is = new InputSource(new StringReader(xmlfeed));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(is);

        NodeList nList = document.getElementsByTagName("item");

        Node nNode = nList.item(0);
        if(nNode.getNodeType() == Node.ELEMENT_NODE){
            Element eElement = (Element) nNode;

            BroadcastStationItem sbs = new BroadcastStationItem();

            SbsNewsItem sbsNewsItem = setNews.getSbsNewsItem(eElement, sbs);

            Assertions.assertThat(sbsNewsItem.getTitle()).isEqualTo("靑, 전광훈 확진에 \"우려가 현실이 됐다…비협조 결과\"");
        }
    }

}
