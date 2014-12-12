package model;

import java.io.InputStream;
import java.util.List;

public interface StoreInformationDAO {
	public abstract StoreInformationBean findByPrimeKey(String storeId);

	public abstract List<StoreInformationBean> getAll();

	public abstract StoreInformationBean insert(StoreInformationBean bean,
			InputStream is, long size);

	public abstract StoreInformationBean update(StoreInformationBean bean,
			InputStream is, long size);

	public abstract boolean delete(String storeId);
}
