import java.util.Arrays;

class Solution {
    public long maxWeight(int[] pizzas) {
        int length = pizzas.length;
        
      
        Arrays.sort(pizzas);
        
        long weight = 0;
        int days = length / 4; 
        int oddDays = days / 2 + days % 2; 
        int evenDays = days - oddDays; 
        for (int i = 0; i < oddDays; i++) {
            weight += pizzas[length - i - 1];
        }
        for (int j = 0; j < evenDays; j++) {
            weight += pizzas[length - oddDays - 2 - j * 2];
        }

        return weight;
    }
}