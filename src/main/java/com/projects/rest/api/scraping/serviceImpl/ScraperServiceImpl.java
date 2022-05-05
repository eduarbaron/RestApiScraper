package com.projects.rest.api.scraping.serviceImpl;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.projects.rest.api.scraping.models.Product;
import com.projects.rest.api.scraping.service.IScraperService;

@Service
public class ScraperServiceImpl implements IScraperService {

	@Value("${website.url.mercadolibre}")
	String mercadolibre;

	@Override
	public Set<Product> getAllPlayStationFive() {

		Set<Product> productDTOs = new HashSet<>();

		if (!mercadolibre.isEmpty()) {

			try {

				Document document = Jsoup.connect(mercadolibre).get();

				Elements lis = document.getElementsByClass("ui-search-layout__item");

				for (Element li : lis) {

					Product productDto = new Product();
					Elements as = li.getElementsByTag("a");

					// Por cada <a>
					for (Element a : as) {

						if (!StringUtils.isEmpty(a.attr("title"))) {
							productDto.setTitle(a.attr("title"));
							productDto.setUrl(a.attr("href"));
						}
					}
					
					// Set offer price
					productDto = getPricePlayFive(li, productDto);					

					if (productDto.getUrl() != null && productDto.getOff() != null) {
						productDTOs.add(productDto);
					}
				}

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return productDTOs;
	}

	private Product getPricePlayFive(Element element, Product productDto) {

		// This return a List with one Element, because just exist one tag with name "ui-search-price__second-line"
		Elements prices = element.getElementsByClass("ui-search-price__second-line");
		
		// Just exist one tag with name "price-tag-fraction" include into "ui-search-price__second-line"
		Elements price = prices.get(0).getElementsByClass("price-tag-fraction");
		
		productDto.setOfferPrice(price.get(0).text());
		
		// Just exist one tag with name "ui-search-price__second-line__label" include into "ui-search-price__second-line"
		Elements offs = prices.get(0).getElementsByClass("ui-search-price__second-line__label");
		
		Element off = offs.get(0);
		
		if (!off.childNodes().isEmpty()) {
			// Just exist one tag with name "ui-search-price__discount" include into "ui-search-price__second-line__label"
			Elements values = off.getElementsByClass("ui-search-price__discount");
			productDto.setOff(values.get(0).text());
		}
		
		return productDto;

	}

}
