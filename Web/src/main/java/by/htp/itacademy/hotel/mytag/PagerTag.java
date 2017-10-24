package by.htp.itacademy.hotel.mytag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import by.htp.itacademy.hotel.domain.vo.ListPage;
import static by.htp.itacademy.hotel.util.Parameter.*;

/**
 * An object of this class implements a tag that creates a pagination.
 * 
 * @author Viktor
 *
 */
public class PagerTag extends SimpleTagSupport {

	private static final String WORD_PAGE = " : </span> <span>&emsp;&emsp;</span>";
	private static final String FIRST_PART_LINK = "<a href=\"#\"";
	private static final String CALLING_FUNCTION = "\" onclick=\"selectPage('";
	private static final String ONE_SPACE_A = "</a> <span>&emsp;</span>";
	private static final String ONE_SPACE_B = "</b> <span>&emsp;</span> ";
	private static final String BRACKETS = ")\">";
	private static final String TAG_B = "<b>";
	private static final String COMMA = "',";
	private static final String TAG_BR = "<br><br>";
	private static final String PAGE_RU = "С т р а н и ц а";
	private static final String PAGE_EN = "P a g e";
	private static final String TAG_SPAN = "<span>";
	private ListPage<?> page;
	private String command;
	private String language;

	public ListPage<?> getPage() {
		return page;
	}

	public void setPage(ListPage<?> page) {
		this.page = page;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public void doTag() throws JspException, IOException {
		Long pagesTotal = page.getTotal() / page.getMaxPerPage();
		if (page.getTotal() % page.getMaxPerPage() != 0) {
			pagesTotal++;
		}
		if (pagesTotal > 1) {
			StringBuffer outputLine = new StringBuffer();
			outputLine = formationOutputLine(outputLine, pagesTotal);
			getJspContext().getOut().append(outputLine.toString());
		}
	}

	/**
	 * Output line generation. <br>
	 * 
	 * @param outputLine
	 * @param pagesTotal
	 * @return
	 */
	private StringBuffer formationOutputLine(StringBuffer outputLine, Long pagesTotal) {
		outputLine.append(TAG_SPAN);
		outputLine.append(fetchWordPage());
		outputLine.append(WORD_PAGE);
		for (int i = 0; i < pagesTotal; i++) {
			int increment = i + 1;
			if (page.getPage() != i) {
				outputLine.append(FIRST_PART_LINK);
				outputLine.append(CALLING_FUNCTION + command + COMMA + i + BRACKETS);
				outputLine.append(increment + ONE_SPACE_A);
			} else {
				outputLine.append(TAG_B + increment + ONE_SPACE_B);
			}
		}
		outputLine.append(TAG_BR);
		return outputLine;
	}

	/**
	 * The method returns the word "Page" in the desired language.
	 * 
	 * @return
	 */
	private StringBuffer fetchWordPage() {
		StringBuffer result = new StringBuffer(PAGE_EN);
		if (language.equals(LANGUAGE_RU)) {
			result = new StringBuffer(PAGE_RU);
		}
		return result;
	}

}
