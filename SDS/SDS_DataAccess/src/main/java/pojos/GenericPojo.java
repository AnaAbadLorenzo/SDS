package pojos;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.hibernate.proxy.HibernateProxyHelper;

import exception.DataBaseException;

public class GenericPojo implements Serializable {

	private static final long serialVersionUID = 1L;

	public GenericPojo() {
	}

	@Override
	public boolean equals(final Object obj) {
		final boolean result = false;
		if (obj != null && HibernateProxyHelper.getClassWithoutInitializingProxy(this) == HibernateProxyHelper
				.getClassWithoutInitializingProxy(obj))
			return true;
		else
			return result;
	}

	public GenericPojo cloneGenericPojo() {
		try {
			final GenericPojo result = getClass().newInstance();
			final Field fields[] = getClass().getDeclaredFields();
			Field afield[];
			final int j = (afield = fields).length;
			for (int i = 0; i < j; i++) {
				final Field field = afield[i];
				field.setAccessible(true);
				if (!field.getName().equals("serialVersionUID") && field.get(this) != null
						&& !field.getName().startsWith("id"))
					field.set(result, field.get(this));
			}

			return result;
		} catch (final Exception e) {
			throw new DataBaseException(e);
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return cloneGenericPojo();
	}

}
