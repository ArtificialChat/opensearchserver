/**   
 * License Agreement for Jaeksoft SearchLib Community
 *
 * Copyright (C) 2008-2009 Emmanuel Keller / Jaeksoft
 * 
 * http://www.jaeksoft.com
 * 
 * This file is part of Jaeksoft SearchLib Community.
 *
 * Jaeksoft SearchLib Community is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 * Jaeksoft SearchLib Community is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Jaeksoft SearchLib Community. 
 *  If not, see <http://www.gnu.org/licenses/>.
 **/

package com.jaeksoft.searchlib.parser;

import java.io.IOException;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.record.TextHeaderAtom;
import org.apache.poi.hslf.usermodel.SlideShow;

public class PptParser extends Parser {

	@Override
	protected void parseContent(LimitInputStream inputStream)
			throws IOException {

		SlideShow ppt = new SlideShow(inputStream);
		Slide[] slides = ppt.getSlides();
		for (Slide slide : slides) {
			TextRun[] textRuns = slide.getTextRuns();
			for (TextRun textRun : textRuns) {
				String field;
				switch (textRun.getRunType()) {
				case TextHeaderAtom.TITLE_TYPE:
				case TextHeaderAtom.CENTER_TITLE_TYPE:
					field = "title";
					break;
				case TextHeaderAtom.NOTES_TYPE:
					field = "note";
					break;
				case TextHeaderAtom.BODY_TYPE:
				case TextHeaderAtom.CENTRE_BODY_TYPE:
				case TextHeaderAtom.HALF_BODY_TYPE:
				case TextHeaderAtom.QUARTER_BODY_TYPE:
					field = "body";
					break;
				case TextHeaderAtom.OTHER_TYPE:
				default:
					field = "other";
					break;
				}
				String[] frags = textRun.getText().split("\\n");
				for (String frag : frags)
					basketDocument.addIfNoEmpty(field, frag.replaceAll("\\s+",
							" "));
			}
		}

	}

	@Override
	protected void parseContent(LimitReader reader) throws IOException {
		throw new IOException("Unsupported");
	}
}
