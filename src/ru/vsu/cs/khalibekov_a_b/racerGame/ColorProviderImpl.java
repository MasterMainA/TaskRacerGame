//package ru.vsu.cs.khalibekov_a_b.racerGame;
//
//import ru.vsu.cs.khalibekov_a_b.racerGame.models.Spectator;
//
//import java.awt.*;
//
//public class ColorProviderImpl implements ColorProvider {
//    private static class SpectatorSchemaImpl implements Spectator.ColorSchema {
//
//        private Color a;
//        private Color b;
//        private double dayTime;
//
//        public void setDayTime(double dayTime) {
//            this.dayTime = dayTime;
//        }
//
//        public SpectatorSchemaImpl(Color a, Color b) {
//            this.a = a;
//            this.b = b;
//        }
//
//        @Override
//        public Color getSkinColor() {
//            return a;// * dayTime;
//        }
//
//        @Override
//        public Color getClothesColor() {
//            return b;
//        }
//    }
//
//    @Override
//    public Spectator.ColorSchema createSpectatorSchema() {
//        return new SpectatorSchemaImpl();
//    }
//
//    @Override
//    public Color createTribuneColor() {
//        return null;
//    }
//}
