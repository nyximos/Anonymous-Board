package eu.tutorials.anonymousboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.format.DateTimeFormatter

/* 어댑터 및 뷰 홀더 구현
    레이아웃을 결정했으면 Adapter 및 ViewHolder를 구현해야 합니다.
    이 두 클래스가 함께 작동하여 데이터 표시 방식을 정의합니다.
    ViewHolder는 목록에 있는 개별 항목의 레이아웃을 포함하는 View의 래퍼입니다.
    Adapter는 필요에 따라 ViewHolder 객체를 만들고 이러한 뷰에 데이터를 설정하기도 합니다.
    뷰를 데이터에 연결하는 프로세스를 바인딩이라고 합니다.
 */
class BoardRecyclerViewAdapter: RecyclerView.Adapter<eu.tutorials.anonymousboard.BoardRecyclerViewAdapter.BoardViewHolder>() {
    private var data:MutableList<Board> = mutableListOf()

    fun setData(data:MutableList<Board>){
        this.data = data
        notifyDataSetChanged()
    }

    class BoardViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById(R.id.title)
        val date: TextView = view.findViewById(R.id.date)
        val views: TextView = view.findViewById(R.id.views)
    }

    /* ViewHolder 만들때 사용하는 함수
    RecyclerView는 ViewHolder를 새로 만들어야 할 때마다 이 메서드를 호출합니다.
    이 메서드는 ViewHolder와 그에 연결된 View를 생성하고 초기화하지만 뷰의 콘텐츠를 채우지는 않습니다.
    ViewHolder가 아직 특정 데이터에 바인딩된 상태가 아니기 때문입니다.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return BoardViewHolder(view)
    }

    /* ViewHolder에 데이터 결합하는 함수
    RecyclerView는 ViewHolder를 데이터와 연결할 때 이 메서드를 호출합니다.
    이 메서드는 적절한 데이터를 가져와서 그 데이터를 사용하여 뷰 홀더의 레이아웃을 채웁니다.
    예를 들어 RecyclerView가 이름 목록을 표시하는 경우 메서드는 목록에서 적절한 이름을 찾아 뷰 홀더의 TextView 위젯을 채울 수 있습니다.
     */
    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {

    val dateTimeFormatter = DateTimeFormatter.ISO_DATE

    val item = data[position]
        holder.title.text = item.title
        holder.date.text = item.createdAt.format(dateTimeFormatter)
        holder.views.text = item.views.toString()
    }

    /*
    RecyclerView는 데이터 세트 크기를 가져올 때 이 메서드를 호출합니다.
    예를 들어 주소록 앱에서는 총 주소 개수가 여기에 해당할 수 있습니다.
    RecyclerView는 이 메서드를 사용하여, 항목을 추가로 표시할 수 없는 상황을 확인합니다.
     */
    override fun getItemCount(): Int {
        return data.size
    }
}