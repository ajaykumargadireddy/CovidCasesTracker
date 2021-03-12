package com.example.CovidTracker.Services;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.CovidTracker.model.CasesStats;

@Service
public class ServiceCorona {
	private List<CasesStats>Updated=new ArrayList<>();
	 private static String url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	@PostConstruct
	@Scheduled(cron="* 1 * * * *")
	public void fetchData() throws IOException, InterruptedException {
		List<CasesStats> temp=new ArrayList<>();
		HttpClient client=HttpClient.newHttpClient();
		HttpRequest request=HttpRequest.newBuilder().uri(URI.create(url)).build();
		HttpResponse<String> response =client.send(request, BodyHandlers.ofString());
		StringReader in=new StringReader(response.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		for (CSVRecord record : records) {
			CasesStats stat=new CasesStats();
		    stat.setState(record.get("Province/State"));
		    stat.setCountry(record.get("Country/Region"));
		    stat.setCases(Integer.parseInt(record.get(record.size()-1)));
		    int today_cases=Integer.parseInt(record.get(record.size()-1));
		    int prev_cases=Integer.parseInt(record.get(record.size()-2));
		    stat.setChangeincases(today_cases-prev_cases);
		    temp.add(stat);
		   // System.out.println(temp.size());
		}
		this.Updated=temp;
		//System.out.println(Updated.size());
	}
	public List<CasesStats> getUpdated() {
		return Updated;
	}
}
