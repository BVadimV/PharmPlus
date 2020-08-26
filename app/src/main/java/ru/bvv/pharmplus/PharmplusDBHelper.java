package ru.bvv.pharmplus;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PharmplusDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "pharma";
    private static final int DB_VERSION = 2;
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_CHARACTERISTIC = "CHARACTERISTIC";
    private static final String COLUMN_COMPOSITION = "COMPOSITION";
    private static final String COLUMN_PHARMACOLOGY = "PHARMACOLOGY_EFFECT";
    private static final String COLUMN_INDICATION = "INDICATION_FOR_USE";
    private static final String COLUMN_CONTRAINDICATION = "CONTRAINDICATIONS";
    private static final String COLUMN_SIDE_EFFECTS = "SIDE_EFFECTS";
    private static final String COLUMN_MODE_OF_APPLICATION = "MODE_OF_APPLICATION";
    private static final String COLUMN_RELEASE_FORM = "RELEASE_FORM";
    private static final String COLUMN_SHELF_LIFE = "SHELF_LIFE";
    private static final String COLUMN_COST = "COST";
    private static final String COLUMN_CATEGORY = "CATEGORY";
    private static final String COLUMN_IMAGE = "IMAGE_RESOURCE_ID";
    private static final String TABLE_NAME = "MEDICINES";

    public PharmplusDBHelper (Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("DROP TABLE MEDICINES");
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private static void insertMedicines(SQLiteDatabase db, String name,
                                        String characteristic, String composition, String pharmacology,
                                        String indications, String contraindications,
                                        String sideEffects, String modeOfApplication,
                                        String releaseForm, String shelfLife,
                                        int cost, String category,
                                        int resourceID){
        ContentValues medicinesValues = new ContentValues();
        medicinesValues.put(COLUMN_NAME, name);
        medicinesValues.put(COLUMN_CHARACTERISTIC, characteristic);
        medicinesValues.put(COLUMN_COMPOSITION, composition);
        medicinesValues.put(COLUMN_PHARMACOLOGY, pharmacology);
        medicinesValues.put(COLUMN_INDICATION, indications);
        medicinesValues.put(COLUMN_CONTRAINDICATION, contraindications);
        medicinesValues.put(COLUMN_SIDE_EFFECTS, sideEffects);
        medicinesValues.put(COLUMN_MODE_OF_APPLICATION, modeOfApplication);
        medicinesValues.put(COLUMN_RELEASE_FORM, releaseForm);
        medicinesValues.put(COLUMN_SHELF_LIFE, shelfLife);
        medicinesValues.put(COLUMN_COST, cost);
        medicinesValues.put(COLUMN_CATEGORY, category);
        medicinesValues.put(COLUMN_IMAGE, resourceID);
        db.insert("MEDICINES", null, medicinesValues);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion < 1){
            db.execSQL("CREATE TABLE MEDICINES (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NAME +" TEXT, "
                    + COLUMN_CHARACTERISTIC + " TEXT, "
                    + COLUMN_COMPOSITION + " TEXT, "
                    + COLUMN_PHARMACOLOGY + " TEXT, "
                    + COLUMN_INDICATION + " TEXT, "
                    + COLUMN_CONTRAINDICATION + " TEXT, "
                    + COLUMN_SIDE_EFFECTS + " TEXT, "
                    + COLUMN_MODE_OF_APPLICATION + " TEXT, "
                    + COLUMN_RELEASE_FORM + " TEXT, "
                    + COLUMN_SHELF_LIFE + " TEXT, "
                    + COLUMN_COST + " INTEGER, "
                    + COLUMN_CATEGORY + " TEXT, "
                    + COLUMN_IMAGE + " INTEGER);");
            insertMedicines(db, "Оргалутран® (Orgalutran)",
                    "Хранить при температуре 2-30°С в защищенном от света и недоступном" +
                            " для детей месте. Не замораживать.",
                    "1 шприц содержит: Активное вещество: ганиреликс (в форме ацетата) 250" +
                            " мкг.Вспомогательные вещества: маннитол - 23.5 мг, уксусная кислота ледяная" +
                            " 99% - 0.1 мг, уксусная кислота ледяная и/или натрия гидроксид - до pH 5.0, вода" +
                            " д/и - q.s. до 5.0 мл.",
                    "Оргалутран является конкурентным антагонистом рецепторов ГнРГ (гонадотропин" +
                            "-рилизинг гормона), контролирует секрецию гонадотропинов (лютеинизирующего (ЛГ) и " +
                            "фолликулостимулирующего (ФСГ) гормонов), тормозит секрецию ЛГ и ФСГ гипофизом (эффект" +
                            " зависит от дозы). В условиях отсутствия предварительной стимуляции, начало угнетения" +
                            " секреторной функции гипофиза наступает сразу после введения препарата и поддерживается" +
                            " при продолжении такого лечения. Вследствие введения женщинам-добровольцам многократных" +
                            " доз по 0,25 мг препарата Оргалутран отмечалось наибольшее снижение концентраций ЛГ, ФСГ" +
                            " и эстрадиола в сыворотке крови на 74%, 32% и 25% при 4, 8 и 16 ч после введения, соответственно." +
                            " Уровень гормонов в сыворотке крови возвращался к исходным значениям в течение двух дней после" +
                            " последней инъекции.Средняя продолжительность применения препарата Оргалутран в дозе 0,25 мг/сут" +
                            " для контролируемой стимуляции овуляции составляет 5 дней. При применении препарата Оргалутран в дозе" +
                            " 0,25 мг/сут, среднее значение частоты повышения уровня ЛГ (более 10 МЕ/л) с сопутствующим увеличением" +
                            " концентрации прогестерона (более 1 нг/мл) составляет 1,2%, что сопоставимо с аналогичным показателем" +
                            " 0,8 % для агонистов ГнРГ. Иногда, еще до начала терапии препаратом Оргалутран, к 6 дню стимуляции яичников" +
                            " гонадотропными препаратами, у женщин с выраженной реакцией яичников наблюдалось повышение уровня ЛГ," +
                            " которое в дальнейшем существенно не влияло на клинические результаты. Секреция ЛГ у этих пациенток быстро" +
                            " подавлялась сразу же после первого введения препарата Оргалутран.",
                    "- Профилактика преждевременного пикового повышения секреции ЛГ у женщин при индукции овуляции (суперовуляции)" +
                            " в программах лечения бесплодия с использованием вспомогательных репродуктивных технологий (ВРТ).",
                    "- Повышенная чувствительность к компонентам препарата;- Повышенная чувствительность к ГнРГ или любому" +
                            " другому аналогу ГнРГ;- Почечная или печеночная недостаточность;- Беременность;- Период лактации.",
                    "Местные реакции:Оргалутран может вызывать местную кожную реакцию в месте введения (преимущественно покраснение," +
                            " как с припухлостью, так и без нее, зуд). В клинических испытаниях через один час после инъекции частота умеренных" +
                            " или серьезных местных кожных реакций на курс лечения составила 12% у пациентов, получавших лечение препаратом Оргалутран." +
                            " Местные реакция обычно исчезают через 4 часа после инъекции.Системные реакции:Ощущение недомогания отмечалось у 0,3 %" +
                            " пациентов.Со стороны иммунной системы:Среди пациентов, которые получали лечение препаратом Оргалутран, были отмечены" +
                            " очень редкие случаи реакций гиперчувствительности (<0,01 %) при введении первой дозы препарата, включающие различные" +
                            " симптомы, такие как кожная сыпь, отек лица и диспноэ.Со стороны нервной системы:Головная боль (0,4 %), головокружение." +
                            "Со стороны желудочно-кишечного тракта:Тошнота (0,5 %), боль в животе.Прочие - слабость.Другие отмечавшиеся нежелательные" +
                            " реакции связаны с лечением, направленным на достижение контролируемой стимуляции овуляции с использованием вспомогательных" +
                            " репродуктивных мероприятий, особенно: боль в малом тазу,вздутие живота, синдром гиперстимуляции яичников (СГЯ) (симптомы:" +
                            " боль внизу живота, рвота, диарея, увеличение яичников, диспноэ, олигурия, увеличение массы тела), эктопическая (внематочная)" +
                            " беременность и самопроизвольный аборт.",
                    "Препарат Оргалутран может назначаться только специалистом-гинекологом. Контролируемая стимуляция овуляции препаратом" +
                            " ФСГ может быть начата на 2 или 3 день менструального цикла. Оргалутран (0,25 мг) вводится инъекционно подкожно один раз в" +
                            " сутки, начиная с 6-го дня применения препарата ФСГ (в клинических исследованиях Оргалутран был использован совместно с" +
                            " рекомбинантным фолликулостимулирующим гормоном-препаратом Пурегон). В случае повышенной реакции яичников на стимуляцию, " +
                            "для предупреждения преждевременного повышения уровня ЛГ, лечение препаратом Оргалутран следует начинать с 5-го дня применения" +
                            " препаратов ФСГ. В случае медленного роста фолликулов введение препарата Оргалутран можно отложить (т.е. начинать позднее 6-го" +
                            " дня применения препаратов ФСГ).Инструкции по введению препаратаОргалутран предназначен для подкожного введения (предпочтительнее " +
                            "в бедро). Нельзя использовать раствор, если он непрозрачен или содержит посторонние примеси.Обработка участка введенияВымойте руки" +
                            " с мылом. Обработайте место инъекции (участок кожи диаметром примерно 5 см вокруг того места, куда должна войти игла) при помощи " +
                            "тампона, пропитанного спиртом, и дайте высохнуть, по крайней мере, 1 минуту.Введение иглыУдалите колпачок с иглы. Указательным и " +
                            "большим пальцами возьмите кожу в складку. Введите иглу в основание складки кожи под углом 45° к ее поверхности. После каждой инъекции" +
                            " меняйте место введения препарата.Проверка правильного положения иглыМягко потяните поршень на себя для того, чтобы проверить," +
                            " правильно ли введена игла. В случае наличия крови в шприце, не вводите препарат, а извлеките шприц, а к месту инъекции приложите" +
                            " тампон, пропитанный спиртом на 1-2 мин. Не используйте этот шприц. Начните введение препарата с новым шприцем.Введение раствораЕсли " +
                            "игла расположена правильно, медленно надавите на поршень так, чтобы раствор был введен подкожно.Извлечение шприцаИзвлеките шприц" +
                            " и сразу же приложите тампон, пропитанный спиртом, прижимая его к месту инъекции. Используйте шприц только один раз.",
                    "Раствор",
                    "2 года",
                    6056,
                    "infertility",
                    R.drawable.med_orgalutran);
            insertMedicines(db, "Хлоргексидин (Chlorhexidine)",
                    "Хранить в прохладном, защищеном от света месте.",
                    "Хлоргексидина раствор",
                    "Активен (бактерициден) в отношении большинства грамположительных и грамотрицательных аэробных и анаэробных бактерий, трепонем, гонококков, трихомонад. Не действует на вирусы и споры.",
                    "Обработка операционного поля и рук хирурга, циститы, раневая инфекция, гингивит, профилактика венерических болезней, стерилизация хирургического инструментария.",
                    "Гиперчувствительность, дерматиты, аллергические реакции.",
                    "Сухость и зуд кожи, дерматиты, липкость рук в течение 3–5 мин, окрашивание зубов, отложение зубного камня, нарушение вкуса (при лечении гингивитов).",
                    "Обработка операционного поля и рук хирурга, циститы, раневая инфекция, гингивит, профилактика венерических болезней, стерилизация хирургического инструментария.",
                    "Раствор",
                    "3 года",
                    50,
                    "infections",
                    R.drawable.med_hlorgek);
        }
    }
}