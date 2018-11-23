package product.utils;

import org.apache.commons.lang.Validate;

public class DividePage {
	private int Pagersize;// ÿһҳ��ʾ������
	private int recoderCount;// ��¼������
	private int currentpager;// ��ǰ��ҳ

	public DividePage(int Pagersize, int recoderCount, int currentpager) {
		// TODO Auto-generated constructor stub
		this.Pagersize = Pagersize;// ÿһҳ����Ŀ
		this.recoderCount = recoderCount;// ����
		setCurrentpager(currentpager);// ���õ�ǰ��ҳ
	}

	public DividePage(int Pagersize, int recoderCount) {
		// TODO Auto-generated constructor stub
		this(Pagersize, recoderCount, 1);// ��ʾ�����ҵ�ǰ��ʾ��ҳ���ǵ�һҳ

	}

	// �����ҳ��
	public int getCountpager() {
		int size = recoderCount / Pagersize;
		int mod = recoderCount % Pagersize;
		if (mod != 0) {
			size++;
		}
		return recoderCount == 0 ? 1 : size;
	}

	// ////////////////////////////
	// ��ʼ��¼������ Ҳ���Ƿ�ҳ����ʼλ�� ��������λ��
	public int getFormIndex() {
		return (currentpager - 1) * Pagersize;
	}

	// ////////////////////////////////////////
	// �����ǽ�����λ��
	public int getTopager() {
		return Pagersize;
	}

	// //////////////////////////////////////////
	// �ɵ�ǰ��ҳ
	public int getCurrentPager() {
		return currentpager;// ���ص�ǰҳ
	}

	// ////////////////////////////////////
	// ���õ�ǰ��ҳ
	public void setCurrentpager(int CurrentPager) {
		// int validpager = CurrentPager <= 0 ? 1 : CurrentPager;//
		// Ҫ�ǵ�ǰҳС�ڵ���0������Ϊ��һҳ
		// validpager = validpager > getCurrentPager() ? getCountpager()
		// : validpager;// Ҫ�ǵ�ǰҳ������ҳ�� ������Ϊ���һҳ
		////////////////////////////////////
		//��֮�����ڷ�Χ�ھ����� 
		int validpager = CurrentPager;
		if (CurrentPager <= 0) {
			validpager = 1;
		} else if (CurrentPager > getCountpager()) {
			validpager = getCountpager();
		}

		this.currentpager = validpager;// ���õ�ǰҳ
	}

	// //////////////////
	// ���ÿһҳ�ļ�¼��Ŀ
	public int getPageSize() {
		return Pagersize;
	}

	// //////////////////////////////////////////
	// ����ÿһҳ�ļ�¼��Ŀ
	public void setPagerSize(int pagerSize) {
		this.Pagersize = pagerSize;
	}

	// ////////////////////////////////////////
	// ����ҵļ�¼����
	public int getRecoderCount() {
		return recoderCount;//
	}

	// ///////////////////////////////
	// �����ҵļ�¼����
	public void setRecoderCount(int recoderCount) {
		this.recoderCount = recoderCount;
	}
}
