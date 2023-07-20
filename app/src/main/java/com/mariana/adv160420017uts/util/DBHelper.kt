package com.mariana.adv160420017uts.util

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mariana.adv160420017uts.model.Donation
import com.mariana.adv160420017uts.model.DonationHistory
import com.mariana.adv160420017uts.model.Subscription
import com.mariana.adv160420017uts.model.User
import org.json.JSONObject

fun addDonationData(): ArrayList<Donation> {
    val str = """
        { "data": [
           {
            "id": "1",
            "title": "Bantu Generasi Muda melanjutkan Pendidikan",
            "penggalang": "Junita",
            "terkumpul": "Rp98.456.387",
            "goals": "Rp200.000.000",
            "deskripsi": "Lorem Ipsum dolor sit amet",
            "hari": "60",
            "photo_url": "https://cdn1-production-images-kly.akamaized.net/6Ticnyku92G5eASXDLvTSwvlqN0=/640x360/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/1203129/original/008505600_1460629198-382805-900-1457598379-eyeem-79683330.jpg"
          },
          {
            "id": "2",
            "title": "Sembuhkan anak saya",
            "penggalang": "Mariana",
            "terkumpul": "Rp135.897.354",
            "goals": "Rp250.000.000",
            "deskripsi": "Lorem Ipsum dolor sit amet",
            "hari": "46",
            "photo_url": "https://d1bpj0tv6vfxyp.cloudfront.net/articles/819014_12-1-2021_12-36-44.jpeg"
          },
          {
            "id": "3",
            "title": "Sel kanker membunuh",
            "penggalang": "Satu Jiwa",
            "terkumpul": "Rp115.456.387",
            "goals": "Rp200.000.000",
            "deskripsi": "Lorem Ipsum dolor sit amet",
            "hari": "50",
            "photo_url": "https://images-cdn.welcomesoftware.com/Zz01OWMzZTc5ZTAwOTUxMWVkYjdhMjg2ZjljNmIwZDc1Zg==/Inilah%205%20Jenis%20Kanker%20yang%20Paling%20Banyak%20Menyerang%20Rakyat%20Indonesia.jpg"
          },
          {
            "id": "4",
            "title": "Benjolan Tumor di Leher",
            "penggalang": "Satu Jiwa",
            "terkumpul": "Rp15.456.387",
            "goals": "Rp270.000.000",
            "deskripsi": "Lorem Ipsum dolor sit amet",
            "hari": "150",
            "photo_url": "https://res.cloudinary.com/dk0z4ums3/image/upload/v1649473809/attached_image/mengenal-manfaat-dan-risiko-operasi-tumor-otak-dengan-fluorescence.jpg"
          },
          {
            "id": "5",
            "title": "Alami Jantung Bocor",
            "penggalang": "Satu Jiwa",
            "terkumpul": "Rp4.468.387",
            "goals": "Rp36.000.000",
            "deskripsi": "Lorem Ipsum dolor sit amet",
            "hari": "78",
            "photo_url": "https://fastcdn.benihbaik.com/campaign/thumbnail/benihbaik_2020-11-06_1604649028_PT_5fa50044c2a6c.jpg"
          },
          {
            "id": "6",
            "title": "Sedekah untuk warga membutuhkan",
            "penggalang": "Satu Jiwa",
            "terkumpul": "Rp225.730.937",
            "goals": "Rp500.000.000",
            "deskripsi": "Lorem Ipsum dolor sit amet",
            "hari": "90",
            "photo_url": "https://lirboyo.net/wp-content/uploads/2021/12/Sedekah-tidak-tepat.jpg"
          }
          ]
        }
    """.trimIndent()

    val json = JSONObject(str)
    val mType = object : TypeToken<ArrayList<Donation>>() { }.type
    val donationList = Gson().fromJson<ArrayList<Donation>>(json.getString("data"), mType)
    Log.d("Helper", donationList.toString())

    return donationList
}

fun addUserData(): ArrayList<User> {
    val str = """
        {
            "data": [
                  {
                    "username": "marianajunita",
                    "password": "Junita",
                    "gender": "2",
                    "telp": "+62817390271",
                    "saldo": "90000",
                    "photo_url": "https://pbs.twimg.com/media/FjU2lkcWYAgNG6d.jpg"
                  },
                  {
                    "username": "katheryn",
                    "password": "Katheryn",
                    "gender": "2",
                    "telp": "+62817390271",
                    "saldo": "100000",
                    "photo_url": "https://pbs.twimg.com/media/FjU2lkcWYAgNG6d.jpg"
                  },
                  {
                    "username": "john",
                    "password": "John2345",
                    "gender": "1",
                    "telp": "+62817390271",
                    "saldo": "70000",
                    "photo_url": "https://pbs.twimg.com/media/FjU2lkcWYAgNG6d.jpg"
                  }
            ]
        }
    """.trimIndent()

    val json = JSONObject(str)
    val mType = object : TypeToken<ArrayList<User>>() { }.type
    val userList = Gson().fromJson<ArrayList<User>>(json.getString("data"), mType)
    Log.d("Helper", userList.toString())

    return userList
}

fun addDonationHistoryData(): ArrayList<DonationHistory> {
    val str = """
        {
            "data": [
                  {
                    "id": "1",
                    "donation_id": "1",
                    "user_username": "marianajunita",
                    "total": "300000",
                    "date": "29 Agt 2022"
                  },
                  {
                    "id": "2",
                    "donation_id": "1",
                    "user_username": "katheryn",
                    "total": "205000",
                    "date": "29 Sep 2022"
                  },
                  {
                    "id": "3",
                    "donation_id": "2",
                    "user_username": "marianajunita",
                    "total": "350000",
                    "date": "29 Agt 2022"
                  },
                  {
                    "id": "4",
                    "donation_id": "2",
                    "user_username": "katheryn",
                    "total": "356000",
                    "date": "29 Dec 2022"
                  }
            ]
        }
    """.trimIndent()

    val json = JSONObject(str)
    val mType = object : TypeToken<ArrayList<DonationHistory>>() { }.type
    val historyList = Gson().fromJson<ArrayList<DonationHistory>>(json.getString("data"), mType)
    Log.d("Helper", historyList.toString())

    return historyList
}

fun addSubscriptionData(): ArrayList<Subscription> {
    val str = """
        {
            "data": [
                  {
                    "id": "1",
                    "title": "BantuPasien Donasi Kebutuhan Pasien Tiap Bulan",
                    "dana": "30.000",
                    "photo_url": "https://v-images2.antarafoto.com/bantu-pasien-langka-lmmk01-prv.jpg"
                  },
                  {
                    "id": "2",
                    "title": "TanamPohon Setiap Bulannya",
                    "dana": "130.000",
                    "photo_url": "https://cdn0-production-images-kly.akamaized.net/FbCvl4w0i5HSUd4GGKm-H46O4xk=/640x360/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/4232811/original/046945700_1668945623-close-up-picture-hand-holding-planting-sapling-plant_1_.jpg"
                  },
                  {
                    "id": "3",
                    "title": "TanamBunga Bantu membuat taman",
                    "dana": "50.000",
                    "photo_url": "https://blogpictures.99.co/cara-menanam-bunga-header.jpg"
                  }
            ]
        }
    """.trimIndent()

    val json = JSONObject(str)
    val mType = object : TypeToken<ArrayList<Subscription>>() { }.type
    val subscriptionList = Gson().fromJson<ArrayList<Subscription>>(json.getString("data"), mType)
    Log.d("Helper", subscriptionList.toString())

    return subscriptionList
}