package service;

import java.util.List;
import java.util.Map;

public interface QueryQuestionService {
	public List<Map<String, Object>> listQuestion(int number);
	public List<Map<String, Object>> listQuestion();
}
