package simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import automail.MailItem;
import automail.MailPool;
import automail.Tube;

@SuppressWarnings("unused")
public class FoodGenerator extends MailGenerator {

	public FoodGenerator(int mailToCreate, int mailMaxWeight, MailPool mailPool, HashMap<Boolean, Integer> seed) {
		super(mailToCreate, mailMaxWeight, mailPool, seed);
	}
}
