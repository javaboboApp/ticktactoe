package com.example.ticktock.models.Board

class Cell(

) {
    var player_id: Int = 0
    private var horizontal: Int = 0
    private var vertical: Int = 0
    private var diagonal: Int = 0

    //Look fo the the next cell position in [diagonal, vertical, and horizontal]
    fun nextTo(cell: Cell) {
        if(cell.player_id == this.player_id ){

        }
    }



}
