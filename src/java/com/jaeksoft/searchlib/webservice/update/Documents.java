/**   
 * License Agreement for OpenSearchServer
 *
 * Copyright (C) 2011-2013 Emmanuel Keller / Jaeksoft
 * 
 * http://www.open-search-server.com
 * 
 * This file is part of OpenSearchServer.
 *
 * OpenSearchServer is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 * OpenSearchServer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenSearchServer. 
 *  If not, see <http://www.gnu.org/licenses/>.
 **/
package com.jaeksoft.searchlib.webservice.update;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import com.jaeksoft.searchlib.analysis.LanguageEnum;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Documents {

	@XmlElement(name = "document")
	public List<Document> documents;

	@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
	public static class Document {

		@XmlAttribute(required = true)
		public LanguageEnum lang;

		@XmlElement(name = "value")
		public List<Value> value;

		@XmlElement(name = "values")
		public List<Values> values;

	}

	@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
	public static class Value {

		@XmlAttribute
		public String field;

		@XmlAttribute
		public Float boost;

		@XmlValue
		public String content;
	}

	@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
	public static class Values {

		@XmlAttribute
		public String field;

		@XmlAttribute
		public Float boost;

		@XmlElement(name = "value", required = true)
		public List<Value> value;

	}

}