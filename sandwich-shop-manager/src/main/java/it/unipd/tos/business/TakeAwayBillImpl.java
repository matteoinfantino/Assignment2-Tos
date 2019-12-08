////////////////////////////////////////////////////////////////////
// [MATTEO] [INFANTINO] [1143705]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;

public class TakeAwayBillImpl implements TakeAwayBill{
        public double getOrderPrice(List<MenuItem> itemsOrdered)
                throws TakeAwayBillException{
                if(itemsOrdered.size() > 30){
                        throw new TakeAwayBillException("Il numero di " +
                                "ordinazioni non può essere maggiore di 30");
                }
                double total = 0;
                double totalPaniniFritti = 0;
                int panini = 0;
                double minPanino = Integer.MAX_VALUE;
                for(MenuItem item : itemsOrdered) {
                        total = total += item.getPrice();
                        if(item.getType() == MenuItem.itemType.PANINI || 
                           item.getType() == MenuItem.itemType.FRITTI) {
                                totalPaniniFritti += item.getPrice();
                        }
                        if (item.getType() == MenuItem.itemType.PANINI) {
                                panini++;
                                if(item.getPrice() < minPanino){
                                        minPanino = item.getPrice();
                                }
                        }
                }
                if(panini >= 5){
                        total -= minPanino * 0.5;
                }
                if(totalPaniniFritti >= 50.0){
                        total = total * 0.9;
                }
                else
                        if(total < 10){
                                total += 0.5;
                        }
                return total;
        }
}
