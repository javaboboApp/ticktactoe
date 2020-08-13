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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cell

        if (player_id != other.player_id || player_id == 0) return false

        return true
    }

    override fun hashCode(): Int {
        return player_id
    }


}
