(def items
	[{:name "Laptop",     :value 2500, :discount 10, :qty 1},
	 {:name "TV",         :value 1000, :discount 0,  :qty 2},
	 {:name "SmartPhone", :value 1350, :discount 0,  :qty 6},
	 {:name "Tablet",     :value 1200, :discount 0,  :qty 3},
	 {:name "Headphone",  :value 100,  :discount 20, :qty 4},
	 {:name "Router",     :value 65,   :discount 0,  :qty 3}])

(defn calculate-discount [value discount qty] (* qty (- value (* value (/ discount 100.)))))

(defn get-values [items]
	(map :value items))

(defn get-discounts [items]
	(map :discount items))

(defn get-qty [items]
	(map :qty items))

(defn calculate-all [items]
	(map calculate-discount (get-values items) (get-discounts items) (get-qty items)))

(defn finalize [items]
	(reduce + (calculate-all items)))

(defn discount-items [items]
	(filter #(> (:discount %) 0) items))