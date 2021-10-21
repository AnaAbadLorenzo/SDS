package com.sds.utilidades;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sds.pojos.GenericPojo;

public class Collections {

	public static List castList(final Class clazz, final Collection c) throws ClassCastException {
		final List r = new ArrayList(c.size());
		Object o;
		for (final Iterator iterator = c.iterator(); iterator.hasNext(); r.add(clazz.cast(o)))
			o = iterator.next();

		return r;
	}

	public static GenericPojo castItem(final Class clazz, final Object c) throws ClassCastException {
		return (GenericPojo) clazz.cast(c);
	}

	public static Map getFieldMapFromObject(final Object o) {
		final Field fields[] = o.getClass().getDeclaredFields();
		final Map fieldMap = new HashMap();
		try {
			Field afield[];
			final int j = (afield = fields).length;
			for (int i = 0; i < j; i++) {
				final Field field = afield[i];
				fieldMap.put(field.getName(), field.get(o));
			}

		} catch (final IllegalAccessException e) {
			e.printStackTrace();
		}
		return fieldMap;
	}

	public static void setNulls(final Object o) {
		final Field fields[] = o.getClass().getDeclaredFields();
		try {
			Field afield[];
			final int j = (afield = fields).length;
			for (int i = 0; i < j; i++) {
				final Field field = afield[i];
				field.setAccessible(true);
				if (!field.getName().equals("serialVersionUID") && field.get(o) != null
						&& !field.getName().startsWith("id"))
					field.set(o, null);
			}

		} catch (final IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
