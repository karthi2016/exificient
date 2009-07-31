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
import com.siemens.ct.exi.datatype.charset.XSDDoubleCharacterSet;
import com.siemens.ct.exi.datatype.strings.StringEncoder;
import com.siemens.ct.exi.io.channel.EncoderChannel;
import com.siemens.ct.exi.util.ExpandedName;
import com.siemens.ct.exi.util.datatype.XSDFloat;

/**
 * TODO Description
 * 
 * @author Daniel.Peintner.EXT@siemens.com
 * @author Joerg.Heuer@siemens.com
 * 
 * @version 0.3.20080718
 */

public class DatatypeFloat extends AbstractDatatype {
	
	protected XSDFloat lastValidFloat = XSDFloat.newInstance();
	
	public DatatypeFloat(ExpandedName datatypeIdentifier) {
		super(BuiltInType.FLOAT, datatypeIdentifier);
		this.rcs = new XSDDoubleCharacterSet();
	}
	
	public boolean isValid(String value) {
		return lastValidFloat.parse(value);
	}

	public void writeValue(EncoderChannel valueChannel, StringEncoder stringEncoder, NameContext context)
			throws IOException {
		valueChannel.encodeFloat(lastValidFloat.mantissa, lastValidFloat.exponent);
	}
}