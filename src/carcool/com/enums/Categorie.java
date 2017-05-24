package carcool.com.enums;

public enum Categorie {
	C {
		@Override
		public String toString() {
			return "Conducteur";
		}
	},
	P {
		@Override
		public String toString() {
			return "Passager";
		}
	},
	BOTH {
		@Override
		public String toString() {
			return "Conducteur ou Passager";
		}
	}
}
