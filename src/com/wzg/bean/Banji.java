package com.wzg.bean;

/**
 * 班级
 * 
 * @author mac
 *
 */
public class Banji extends Entity {

	private static final long serialVersionUID = 8816154554967084642L;
	private int create_id;
	private String create_name;

	private String guo_jia; // 国家
	private String sheng_shi; // 省市
	private String xian; // 县
	private String qu; // 区
	private String xue_xiao; // 学校名
	private String icon;
	private int begin_year;
	private int end_year;
	private String banji_name; // 班级名

	public Banji() {
		super();
	}

	public Banji(int id, int create_id, String create_name, String guo_jia, String sheng_shi, String xian, String qu, String xue_xiao, String icon,
			int begin_year, int end_year, String banji_name) {
		super();
		this.id = id;
		this.create_id = create_id;
		this.create_name = create_name;
		this.guo_jia = guo_jia;
		this.sheng_shi = sheng_shi;
		this.xian = xian;
		this.qu = qu;
		this.xue_xiao = xue_xiao;
		this.icon = icon;
		this.begin_year = begin_year;
		this.end_year = end_year;
		this.banji_name = banji_name;

	}

	public int getCreate_id() {
		return create_id;
	}

	public void setCreate_id(int create_id) {
		this.create_id = create_id;
	}

	public String getCreate_name() {
		return create_name;
	}

	public void setCreate_name(String create_name) {
		this.create_name = create_name;
	}

	public String getGuo_jia() {
		return guo_jia;
	}

	public void setGuo_jia(String guo_jia) {
		this.guo_jia = guo_jia;
	}

	public String getSheng_shi() {
		return sheng_shi;
	}

	public void setSheng_shi(String sheng_shi) {
		this.sheng_shi = sheng_shi;
	}

	public String getXian() {
		return xian;
	}

	public void setXian(String xian) {
		this.xian = xian;
	}

	public String getQu() {
		return qu;
	}

	public void setQu(String qu) {
		this.qu = qu;
	}

	public String getXue_xiao() {
		return xue_xiao;
	}

	public void setXue_xiao(String xue_xiao) {
		this.xue_xiao = xue_xiao;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getBegin_year() {
		return begin_year;
	}

	public void setBegin_year(int begin_year) {
		this.begin_year = begin_year;
	}

	public int getEnd_year() {
		return end_year;
	}

	public void setEnd_year(int end_year) {
		this.end_year = end_year;
	}

	public String getBanji_name() {
		return banji_name;
	}

	public void setBanji_name(String banji_name) {
		this.banji_name = banji_name;
	}

	@Override
	public String toString() {
		return "Banji [id=" + id + ", create_id=" + create_id + ", create_name=" + create_name + ", guo_jia=" + guo_jia + ", sheng_shi=" + sheng_shi
				+ ", xian=" + xian + ", qu=" + qu + ", xue_xiao=" + xue_xiao + ", icon=" + icon + ", begin_year=" + begin_year + ", end_year=" + end_year
				+ ", banji_name=" + banji_name + "]";
	}

}
