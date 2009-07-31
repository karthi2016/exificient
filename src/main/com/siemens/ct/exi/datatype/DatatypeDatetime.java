/*
 * Copyright (C) 2007-2009 Siemens AG
 *
 * This program and its interfaces are free software;
 * you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.siemens.ct.exi.datatype;

import java.io.IOException;

import com.siemens.ct.exi.core.NameContext;
import com.siemens.ct.exi.datatype.charset.XSDDateTimeCharacterSet;
import com.siemens.ct.exi.datatype.strings.StringEncoder;
import com.siemens.ct.exi.io.channel.EncoderChannel;
import com.siemens.ct.exi.util.ExpandedName;
import com.siemens.ct.exi.util.datatype.DatetimeType;
import com.siemens.ct.exi.util.datatype.XSDDatetime;

/**
 * TODO Description
 * 
 * @author Daniel.Peintner.EXT@siemens.com
 * @author Joerg.Heuer@siemens.com
 * 
 * @version 0.3.20080718
 */

public class DatatypeDatetime extends AbstractDatatype {
	DatetimeType datetimeType;
	
	private XSDDatetime lastValidDatetime = XSDDatetime.newInstance();

	public DatatypeDatetime(DatetimeType dateType,
			ExpandedName datatypeIdentifier) {
		super(BuiltInType.DATETIME, datatypeIdentifier);
		this.rcs = new XSDDateTimeCharacterSet();
		this.datetimeType = dateType;
	}

	public DatetimeType getDatetimeType() {
		return datetimeType;
	}
	
	public boolean isValid(String value) {
		return lastValidDatetime.parse(value, datetimeType);
	}

	public void writeValue(EncoderChannel valueChannel, StringEncoder stringEncoder, NameContext context)
			throws IOException {
		valueChannel.encodeDateTime(lastValidDatetime);
	}

}