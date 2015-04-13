package uk.co.clickcomputing.hhscweatherstation;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DashBoard extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    DashAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.dash_board, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) layout.findViewById(R.id.swipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView mRecycleView = (RecyclerView) layout.findViewById(R.id.dashRecycler);
        adapter = new DashAdapter(getActivity(), getData());
        mRecycleView.setAdapter(adapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }

    private String[] getData() {
        try {
            return new RSSReader().execute().get().split(";");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onRefresh() {
        adapter.updateData(getData());
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private class RSSReader extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... params) {
            String downloadUrl = "http://www.hillheadsc.org.uk/weewx/RSS/weewx_rss.xml";
            try {
                URL url = new URL(downloadUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream inputStream = connection.getInputStream();
                return processXML(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

            return null;
        }

        public String processXML(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document xmlDocument =  documentBuilder.parse(inputStream);
            Element rootElement = xmlDocument.getDocumentElement();
            NodeList itemList = rootElement.getElementsByTagName("item");
            Node node = itemList.item(0);
            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++){
                Node current = children.item(i);
                if (current.getNodeName().equalsIgnoreCase("description")){
                    return current.getTextContent();
                }
            }
            return null;
        }
    }
}
