package by.htp.itacademy.hotel.domain.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * A list wrapper with the information we need.
 * 
 * @author Viktor
 *
 * @param <T>
 */
@Data
public class ListPage<T> implements Serializable {

	private static final long serialVersionUID = -5481698637370816315L;

	private Long page;
	private List<T> data;
	private Long total;
	private Long maxPerPage;
	private String command;

	public ListPage(Long page, Long maxPerPage, String command) {
		super();
		this.page = page;
		this.maxPerPage = maxPerPage;
		this.command = command;
	}

	public ListPage(List<T> data, Long page, Long total, Long maxPerPage, String command) {
		super();
		this.page = page;
		this.data = data;
		this.total = total;
		this.maxPerPage = maxPerPage;
		this.command = command;
	}

}
