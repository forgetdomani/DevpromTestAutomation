package testData;

import java.util.Random;

public class TestScenario {
	String name;

	public TestScenario() {
		this.name = generateScenarioName();
	}

	private String generateScenarioName() {
		String strAllowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			sb.append(strAllowedCharacters.charAt(random.nextInt(strAllowedCharacters.length())));
		}
		return "Scenario " + sb.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
