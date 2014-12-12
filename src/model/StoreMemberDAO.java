package model;

import java.io.InputStream;
import java.util.List;

public interface StoreMemberDAO {
	public abstract StoreMemberBean findByPrimeKey(String storeUsername);

	public abstract List<StoreMemberBean> getAll();

	public abstract StoreMemberBean insert(StoreMemberBean bean,
			InputStream is, long size);

	public abstract StoreMemberBean update(StoreMemberBean bean,
			InputStream is, long size);

	public abstract boolean delete(String storeUsername);
}
