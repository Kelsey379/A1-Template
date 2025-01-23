//package ca.mcmaster.se2aa4.mazerunner;
//
//import ca.mcmaster.se2aa4.mazerunner.wip.MapPosition;
//
//import static ca.mcmaster.se2aa4.mazerunner.Maze.maze;
//
//public class LocateEntryExitPoints {
//
//    public MapPosition startPoint;
//    public MapPosition endPoint;
//
//    private MapPosition mazeStart() throws Exception {
//        for (int i = 0; i<maze.size(); i++ ){
//            MapPosition position = new MapPosition(0, i);
//            if (maze.get(position.y()).get(position.x())){
//                startPoint = position;
//                return startPoint;
//            }
//        }
//
//        throw new Exception("Maze has no entry points");
//    }
//
//    private MapPosition mazeEnd() throws Exception {
//        for (int i = 0; i<maze.size(); i++ ){
//            MapPosition position = new MapPosition(maze.get(0).size()-1, i);
//            if (maze.get(position.y()).get(position.x())){
//                endPoint = position;
//                return endPoint;
//            }
//        }
//
//        throw new Exception("Maze has no exit points");
//    }
//
//}
