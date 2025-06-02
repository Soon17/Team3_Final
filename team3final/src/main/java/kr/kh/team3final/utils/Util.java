package kr.kh.team3final.utils;

public class Util {
	public static String parseDate(String checkTime) {
	// 예: "2025.06.02(월)"
		String datePart = checkTime.substring(5, 10); // "06.02"
		String[] dateSplit = datePart.split("\\.");
		String month = String.valueOf(Integer.parseInt(dateSplit[0])); // 앞자리 0 제거
		String day = dateSplit[1];
		String dayOfWeek = checkTime.substring(checkTime.indexOf('(') + 1, checkTime.indexOf(')'));

		return month + "/" + day + "/" + dayOfWeek;
	}

	public static String formatDate(String dateStr) {
		// 괄호 앞까지 자르고 점(.)을 하이픈(-)으로 교체
		String dateOnly = dateStr.split("\\(")[0];
		return dateOnly.replace(".", "-");
	}	
}
