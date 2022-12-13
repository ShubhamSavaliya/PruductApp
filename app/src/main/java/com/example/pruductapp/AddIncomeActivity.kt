package com.example.pruductapp

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.pruductapp.databinding.ActivityAddIncomeBinding
import java.util.*
import kotlin.math.log
import com.example.pruductapp.CatogryDatabaseHelper as ProductDatabaseHelper


class AddIncomeActivity() : AppCompatActivity() {
    lateinit var binding: ActivityAddIncomeBinding
    val galleryCode = 100
var TAG = ""
     var date: String ?=null
     var category : String ?=null


    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    var userDatabase = ProductDatabaseHelper(this)
    var incomeExpenseDatabase = IncomeExpenseDatabase(this)
    var type = 0
    var paymentarray = arrayOf("Online", "Offline", "Other")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddIncomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        type = intent.getIntExtra("condition", 0)



        if (type == 0) {
            binding.tnxincome.setText("Income")
            binding.txtIncome.setText("Income")
        } else {
            binding.tnxincome.setText("Expense")
            binding.txtIncome.setText("Expense")
        }


        intiView()
        datePicker()
        timepicker()
        spinneradapter()
        database()
    }

    private fun database() {
        var incomeexpensedatabase = IncomeExpenseDatabase(this)
    }

    private fun spinneradapter() {
        var list = userDatabase.DataDisplay()
        var adapter = SpinnerAdapter(this, list)

        binding.spnCategory.adapter = adapter
        binding.spnCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                Log.d("TAG", "onItemSelected: $position")
                 category= list[position].Name
                Log.e(TAG, "onItemSelected: $category", )
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }
    }

    private fun timepicker() {


        val mTimePicker: TimePickerDialog
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentTime.get(Calendar.MINUTE)

        mTimePicker = TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                binding.txtTimeSet.text = String.format("%d : %d", hourOfDay, minute)
            }
        }, hour, minute, false)

        binding.txtTimeSet.setOnClickListener {
            mTimePicker.show()
        }
        binding.imgTimePicker.setOnClickListener {
            mTimePicker.show()
        }
    }

    private fun datePicker() {


        binding.idBtnPickDate.setOnClickListener {

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(

                this,
                { view, year, monthOfYear, dayOfMonth ->
                    date =
                        (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding.datePicer.text = date

                }, year, month, day
            )

            datePickerDialog.show()
        }
    }

    private fun intiView() {


        val allc =
            ArrayAdapter<Any?>(this, R.layout.spinner_item, R.id.categorySpinner, paymentarray)

        binding.spnPayment.adapter = allc
        imageAdd()
    }

    private fun imageAdd() {


        binding.openCamera.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.beckground_item)
            dialog.window!!
                .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


            val img_gallery = dialog.findViewById<LinearLayout>(R.id.img_gallery)
            val img_camera = dialog.findViewById<LinearLayout>(R.id.img_camera)


            img_gallery.setOnClickListener {
                val galleryIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(
                    Intent.createChooser(galleryIntent, "Select Picture"),
                    galleryCode
                )
                dialog.dismiss()
            }
            dialog.show()
            img_camera.setOnClickListener {
                val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivity(camera_intent)
            }
        }
        binding.imgBack.setOnClickListener {
//            var intent = Intent(this, ChoosingActivity::class.java)
//            startActivity(intent)
            finish()
        }


        binding.btnSubmit.setOnClickListener {
            var amount = binding.edtIncome.text

            Log.e("TAG", "imageAdd3:$category " )
            var name: String? = null
            if (amount == null) {
                binding.edtIncome.error = "Please enter Amount name"
            } else if (date == null) {
                Toast.makeText(this, "Select Date", Toast.LENGTH_SHORT).show()
            } else {
                var position = binding.spnPayment.selectedItemPosition
                var paymentMethod = paymentarray.get(position)
                Toast.makeText(this, "Submit Details", Toast.LENGTH_SHORT).show()
                incomeExpenseDatabase.insert(type, amount, category, paymentMethod, date!!)
                val i = Intent(this, DisplayInExActivity::class.java)
                startActivity(i)
            }
//            notification()
        }
    }

    private fun notification() {
        var notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
////        val intent = Intent(this, ChoosingActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel =
                NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channelId)
//                .setContent(contentView)
                .setContentText("Hye How Are You")
                .setContentTitle("Care Toast")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        this.resources,
                        R.drawable.ic_launcher_background
                    )
                )
                .setContentIntent(pendingIntent)
        } else {

            builder = Notification.Builder(this)
//                .setContent(contentView)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        this.resources,
                        R.drawable.ic_launcher_background
                    )
                )
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(1234, builder.build())

    }
}
