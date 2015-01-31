(ns conway.core)

(defn make-grid [line column]
	(for [c (range line)] (for [r (range column)] 0)))
	
(defn left-of [line column] 
	(for [x (range (- line 1) (+ line 2))] 
		[x (- column 1)]))

(defn right-of [line column height width]
	(cond
		(and (> line 0) (< line (- height 1)) (> column 0) (< column (- width 1)))
			(for [x (range (- line 1) (+ line 2))] 
				[x (+ column 1)])
		))