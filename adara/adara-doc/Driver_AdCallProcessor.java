public class Driver_AdCallProcessor {

	public static final int randomPlacementCall;

    public static void main(String[] args) {

        Random r = new Random();

        randomPlacementCall = r.nextInt();

        AdCallProcessor adCallProcessor = new AdCallProcessor();

        for(int i = 0; i < 100; i++) {

        	adCallProcessor.handleAdCall(randomPlacementCall);
        }

    }
}