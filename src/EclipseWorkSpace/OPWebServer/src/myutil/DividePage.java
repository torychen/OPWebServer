package myutil;


public class DividePage {
	private int Pagersize;// 每一页显示的条数
	private int recoderCount;// 记录的总数
	private int currentpager;// 当前的页

	public DividePage(int Pagersize, int recoderCount, int currentpager) {
		this.Pagersize = Pagersize;// 每一页的数目
		this.recoderCount = recoderCount;// 总数
		setCurrentpager(currentpager);// 设置当前的页
	}

	public DividePage(int Pagersize, int recoderCount) {
		this(Pagersize, recoderCount, 1);// 表示的是我当前显示的页就是第一页
	}

	// 获得总页数
	public int getCountpager() {
		int size = recoderCount / Pagersize;
		int mod = recoderCount % Pagersize;
		if (mod != 0) {
			size++;
		}
		return recoderCount == 0 ? 1 : size;
	}

	// ////////////////////////////
	// 开始记录的条数 也就是分页的起始位置 这里是起位置
	public int getStartIndex() {
		return (currentpager - 1) * Pagersize;
	}

	// ////////////////////////////////////////
	// 这里是结束的位置
	public int getTopager() {
		return Pagersize;
	}

	// //////////////////////////////////////////
	public int getCurrentPager() {
		return currentpager;// 返回当前页
	}

	// ////////////////////////////////////
	// 设置当前的页
	public void setCurrentpager(int CurrentPager) {
		// int validpager = CurrentPager <= 0 ? 1 : CurrentPager;//
		// 要是当前页小于等于0就设置为第一页
		// validpager = validpager > getCurrentPager() ? getCountpager()
		// : validpager;// 要是当前页大于总页数 就设置为最后一页
		////////////////////////////////////
		//总之保存在范围内就行了 
		int validpager = CurrentPager;
		if (CurrentPager <= 0) {
			validpager = 1;
		} else if (CurrentPager > getCountpager()) {
			validpager = getCountpager();
		}

		this.currentpager = validpager;// 设置当前页
	}

	// //////////////////
	// 获得每一页的记录数目
	public int getPageSize() {
		return Pagersize;
	}

	// //////////////////////////////////////////
	// 设置每一页的记录数目
	public void setPagerSize(int pagerSize) {
		this.Pagersize = pagerSize;
	}

	// ////////////////////////////////////////
	// 获得我的记录总数
	public int getRecoderCount() {
		return recoderCount;//
	}

	// ///////////////////////////////
	// 设置我的记录总数
	public void setRecoderCount(int recoderCount) {
		this.recoderCount = recoderCount;
	}
}
