# Fawry-Assignment

A simple Java-based e-commerce cart system that simulates a shopping experience.  
Users can add products, view available items, apply discounts, check shipping weight, and finalize checkout with validation and error handling.

---

## Features

- ✅ Add products to inventory and cart  
- ✅ Handle expired products and insufficient quantities  
- ✅ Apply per-product discounts  
- ✅ Calculate shipping costs based on weight  
- ✅ Show checkout receipt with date, subtotal, shipping, discounts, and total  
- ✅ Print shipping notice and package weight  
- ✅ Handle common exceptions gracefully  
- ✅ Inventory system with live tracking

---

## Exceptions Covered

- ```EmptyCartException``` → trying to checkout an empty cart 
- ```QuantityExceededException``` → not enough quantity in stock
- ```ProductExpiredException``` → expired products cannot be purchased
- ```BalanceExceededException``` → insufficient balance during checkout
- ```ProductNotFoundException``` → when trying to add non-existing product

---
## Sample Output

Welcome Rofyda Mohamed Elghadban to our stock!

Your balance: 2000.00

### ** Available Products **

```
Product                    Price     Quantity  Discount
---------------------------------------------------------
TV                         800.00    5         200.00  
Biscuit                    23.00     4         0.00    
Scratch Card               50.00     20        0.00    
Cheese                     75.00     10        10.00   
---------------------------------------------------------
```

### ** Shipment Notice **

```
Qty    Product                   Weight (g)     
------------------------------------------------------
2x     Biscuit                   5.00           
1x     TV                        5000.00        
3x     Cheese                    776.40         
Total package weight:            5.78 kg
------------------------------------------------------
```

### ** Checkout receipt **

```
Qty    Product              Unit Price   Discount   Total     
-------------------------------------------------------------
2x     Biscuit              23.00        -0.00      46.00     
1x     TV                   800.00       -200.00    600.00    
3x     Cheese               75.00        -10.00     195.00    
1x     Scratch Card         50.00        -0.00      50.00     
-------------------------------------------------------------
```

###

```
Subtotal                  891.00 
Shipping                  57.81 
Discount                  -230.00 
Amount                    718.81 
Remaining balance         1281.19
```

###

Checkout Date: 22:45:30 04/07/2025

Thank you for shopping with us!

---
