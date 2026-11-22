package com.api_test.book.config;

import com.api_test.book.model.Book;
import com.api_test.book.model.Category;
import com.api_test.book.repositories.BookRepository;
import com.api_test.book.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class DataSeederConfig {

    @Bean
    CommandLineRunner seedLibraryData(CategoryRepository categoryRepository, BookRepository bookRepository) {
        return args -> {
            if (categoryRepository.count() > 0 || bookRepository.count() > 0) {
                return;
            }
            seedData(categoryRepository, bookRepository);
        };
    }

    @Transactional
    public void seedData(CategoryRepository categoryRepository, BookRepository bookRepository) {
        if (categoryRepository.count() > 0 || bookRepository.count() > 0) {
            return;
        }

        List<Category> categories = categoryRepository.saveAll(createCategories());
        Map<String, Category> categoriesByName = new HashMap<>();
        for (Category category : categories) {
            categoriesByName.put(category.getName(), category);
        }

        seedBooks(categoriesByName);
        categoryRepository.saveAll(categories);
    }

    private List<Category> createCategories() {
        return Arrays.asList(
            createCategory("Informática", "Programação, desenvolvimento web, banco de dados, IA, cloud computing"),
            createCategory("Matemática", "Álgebra, cálculo, estatística, geometria, matemática discreta"),
            createCategory("Física", "Mecânica, termodinâmica, eletromagnetismo, quântica, relatividade"),
            createCategory("Química", "Orgânica, inorgânica, analítica, físico-química, bioquímica"),
            createCategory("Biologia", "Genética, ecologia, evolução, microbiologia, biologia molecular"),
            createCategory("Medicina", "Anatomia, fisiologia, farmacologia, patologia, clínica médica"),
            createCategory("Engenharia", "Civil, elétrica, mecânica, software, química, ambiental"),
            createCategory("Economia", "Microeconomia, macroeconomia, econometria, finanças, comércio internacional"),
            createCategory("Administração", "Gestão, marketing, RH, estratégia, empreendedorismo, logística"),
            createCategory("Direito", "Constitucional, civil, penal, trabalhista, tributário, internacional"),
            createCategory("Psicologia", "Clínica, social, cognitiva, desenvolvimento, neuropsicologia"),
            createCategory("Filosofia", "Ética, lógica, metafísica, epistemologia, filosofia política"),
            createCategory("História", "Antiga, medieval, moderna, contemporânea, Brasil, militar"),
            createCategory("Geografia", "Física, humana, urbana, ambiental, cartografia, climatologia"),
            createCategory("Literatura Brasileira", "Romance, conto, poesia, modernismo, realismo, contemporânea"),
            createCategory("Literatura Estrangeira", "Romance, conto, poesia, clássicos, best-sellers internacionais"),
            createCategory("Ficção Científica", "Space opera, cyberpunk, distopia, hard sci-fi, space fantasy"),
            createCategory("Fantasia", "Épica, urbana, dark fantasy, high fantasy, low fantasy"),
            createCategory("Suspense/Thriller", "Mistério, policial, noir, psychological thriller, espionagem"),
            createCategory("Terror/Horrror", "Sobrenatural, gore, psicológico, gothic, cosmic horror"),
            createCategory("Biografia/Memórias", "Personalidades históricas, celebridades, cientistas, artistas"),
            createCategory("Autoajuda/Desenvolvimento", "Produtividade, inteligência emocional, hábitos, liderança"),
            createCategory("Artes/Design", "Pintura, escultura, fotografia, design gráfico, arquitetura, música"),
            createCategory("Tecnologia/Inovação", "IA, blockchain, IoT, robotics, VR/AR, startups, transformação digital"),
            createCategory("Ciências Sociais", "Sociologia, antropologia, ciência política, comunicação, educação"),
            createCategory("Idiomas/Linguística", "Inglês, espanhol, francês, alemão, japonês, linguística aplicada"),
            createCategory("Infantil/Juvenil", "Livros ilustrados, YA, middle grade, educativos, contos de fadas"),
            createCategory("Religião/Espiritualidade", "Teologia, budismo, cristianismo, islamismo, meditação, filosofia oriental"),
            createCategory("Gastronomia/Nutrição", "Culinária, dietas, confeitaria, vinhos, culinária regional, saudável"),
            createCategory("Esportes/Lazer", "Futebol, musculação, yoga, corrida, natação, esportes radicais, outdoor")
        );
    }

    @Transactional
    public void seedBooks(java.util.Map<String, Category> catMap) {
        // 1. Informática - 10 livros
        createBooks(catMap.get("Informática"), Arrays.asList(

            new BookData("Clean Code", "Robert C. Martin", "Princípios de desenvolvimento de software limpo, legível e sustentável. Padrões de design, refatoração e boas práticas.", 2008, new BigDecimal("89.90"), "https://covers.openlibrary.org/b/isbn/9780132350884-L.jpg"),
            new BookData("Design Patterns", "Erich Gamma et al.", "Padrões de projeto clássicos para orientação a objetos. Soluções reutilizáveis para problemas comuns de design.", 1994, new BigDecimal("119.90"), "https://covers.openlibrary.org/b/isbn/9780201633610-L.jpg"),
            new BookData("Clean Architecture", "Robert C. Martin", "Arquitetura de software limpa: separação de responsabilidades, independência de frameworks, testabilidade.", 2017, new BigDecimal("94.90"), "https://covers.openlibrary.org/b/isbn/9780134494166-L.jpg"),
            new BookData("Python Fluente", "Luciano Ramalho", "Guia avançado de Python 3: estruturas de dados, funções, classes, metaprogramação, concorrência.", 2021, new BigDecimal("129.90"), "https://covers.openlibrary.org/b/isbn/9781492056355-L.jpg"),
            new BookData("Effective Java", "Joshua Bloch", "Melhores práticas para Java: APIs, generics, enums, lambdas, streams, módulos, concorrência.", 2018, new BigDecimal("119.90"), "https://covers.openlibrary.org/b/isbn/9780134686097-L.jpg"),
            new BookData("Spring Boot em Ação", "Craig Walls", "Desenvolvimento rápido com Spring Boot: auto-configuração, starters, actuators, testes, deploy.", 2020, new BigDecimal("99.90"), "https://covers.openlibrary.org/b/isbn/9781617292545-L.jpg"),
            new BookData("Kubernetes na Prática", "Brendan Burns", "Orquestração de containers: pods, services, deployments, configmaps, secrets, helm.", 2019, new BigDecimal("109.90"), "https://covers.openlibrary.org/b/isbn/9781492047100-L.jpg"),
            new BookData("Machine Learning com Python", "Andreas Müller", "Scikit-learn, pipelines, feature engineering, model evaluation, deployment em produção.", 2020, new BigDecimal("139.90"), "https://covers.openlibrary.org/b/isbn/9781449369415-L.jpg"),
            new BookData("Sistemas Operacionais Modernos", "Andrew Tanenbaum", "Processos, threads, memória, sistemas de arquivos, I/O, deadlocks, virtualização.", 2014, new BigDecimal("149.90"), "https://covers.openlibrary.org/b/isbn/9780133591620-L.jpg"),
            new BookData("Redes de Computadores", "Andrew Tanenbaum", "Camadas físicas, enlace, rede, transporte, aplicação. TCP/IP, roteamento, segurança.", 2010, new BigDecimal("139.90"), "https://covers.openlibrary.org/b/isbn/9780132126953-L.jpg")
        ));

        // 2. Matemática - 10 livros
        createBooks(catMap.get("Matemática"), Arrays.asList(
            new BookData("Cálculo Vol. 1", "James Stewart", "Limites, derivadas, integrais, aplicações. Exercícios graduados, exemplos resolvidos.", 2015, new BigDecimal("189.90"), "https://covers.openlibrary.org/b/isbn/9781305272378-L.jpg"),
            new BookData("Álgebra Linear e Aplicações", "Gilbert Strang", "Matrizes, vetores, autovalores, decomposição SVD, aplicações em ciência de dados.", 2016, new BigDecimal("179.90"), "https://covers.openlibrary.org/b/isbn/9780980232776-L.jpg"),
            new BookData("Probabilidade e Estatística", "Jay Devore", "Variáveis aleatórias, distribuições, estimação, testes de hipóteses, regressão linear.", 2015, new BigDecimal("199.90"), "https://covers.openlibrary.org/b/isbn/9781305251809-L.jpg"),
            new BookData("Matemática Discreta", "Kenneth Rosen", "Lógica, conjuntos, funções, relações, grafos, combinatória, algoritmos, criptografia.", 2018, new BigDecimal("219.90"), "https://covers.openlibrary.org/b/isbn/9781259676512-L.jpg"),
            new BookData("Análise Real", "Walter Rudin", "Fundamentos rigorosos: topologia de R^n, sequências, séries, continuidade, derivadas.", 1976, new BigDecimal("159.90"), "https://covers.openlibrary.org/b/isbn/9780070542358-L.jpg"),
            new BookData("Equações Diferenciais", "Dennis Zill", "EDOs de primeira ordem, segunda ordem, séries de potência, Laplace, sistemas lineares.", 2012, new BigDecimal("179.90"), "https://covers.openlibrary.org/b/isbn/9781111827052-L.jpg"),
            new BookData("Cálculo Numérico", "Richard Burden", "Métodos numéricos: interpolação, integração, EDOs, sistemas lineares, otimização.", 2015, new BigDecimal("189.90"), "https://covers.openlibrary.org/b/isbn/9781305253667-L.jpg"),
            new BookData("Teoria dos Grafos", "Douglas West", "Caminhos, árvores, planaridade, coloração, fluxos, algoritmos, aplicações em redes.", 2000, new BigDecimal("169.90"), "https://covers.openlibrary.org/b/isbn/9780130144003-L.jpg"),
            new BookData("Otimização Linear", "Dimitris Bertsimas", "Programação linear, dualidade, simplex, interior point, análise de sensibilidade.", 1997, new BigDecimal("179.90"), "https://covers.openlibrary.org/b/isbn/9781886529199-L.jpg"),
            new BookData("Matemática Financeira", "Antonio Arnot", "Juros simples/compostos, anuidades, amortização, títulos, derivativos, risk management.", 2019, new BigDecimal("99.90"), "https://covers.openlibrary.org/b/isbn/9788521629587-L.jpg")
        ));

        // 3. Física - 10 livros
        createBooks(catMap.get("Física"), Arrays.asList(
            new BookData("Física Universitária Vol. 1", "Hugh Young", "Mecânica clássica: cinemática, dinâmica, energia, momento, rotação, gravitação.", 2019, new BigDecimal("199.90"), "https://covers.openlibrary.org/b/isbn/9781292314730-L.jpg"),
            new BookData("Eletromagnetismo", "David Griffiths", "Eletrostática, magnetostática, indução, equações de Maxwell, ondas eletromagnéticas.", 2017, new BigDecimal("189.90"), "https://covers.openlibrary.org/b/isbn/9781108420419-L.jpg"),
            new BookData("Mecânica Quântica", "David Griffiths", "Função de onda, Schrödinger, operadores, spin, átomo de hidrogênio, perturbação.", 2018, new BigDecimal("189.90"), "https://covers.openlibrary.org/b/isbn/9781107189638-L.jpg"),
            new BookData("Termodinâmica Estatística", "Reif", "Entropia, ensembles, leis da termodinâmica, funções de partição, transições de fase.", 2009, new BigDecimal("179.90"), "https://covers.openlibrary.org/b/isbn/9780072347938-L.jpg"),
            new BookData("Relatividade Restrita e Geral", "Carroll", "Espaço-tempo, tensores, curvatura, buracos negros, cosmologia, ondas gravitacionais.", 2019, new BigDecimal("179.90"), "https://covers.openlibrary.org/b/isbn/9781108488396-L.jpg"),
            new BookData("Óptica", "Hecht", "Ondas, interferência, difração, polarização, lasers, fibras ópticas, holografia.", 2016, new BigDecimal("189.90"), "https://covers.openlibrary.org/b/isbn/9780133977226-L.jpg"),
            new BookData("Física de Partículas", "Griffiths", "Modelo padrão, quarks, léptons, bósons, simetrias, acoplamentos, LHC.", 2020, new BigDecimal("179.90"), "https://covers.openlibrary.org/b/isbn/9781108478373-L.jpg"),
            new BookData("Física do Estado Sólido", "Kittel", "Estrutura cristalina, fonons, elétrons, bandas, semicondutores, supercondutividade.", 2005, new BigDecimal("189.90"), "https://covers.openlibrary.org/b/isbn/9780471415268-L.jpg"),
            new BookData("Astrofísica", "Carroll & Ostlie", "Estrelas, galáxias, cosmologia, matéria escura, energia escura, Big Bang.", 2017, new BigDecimal("219.90"), "https://covers.openlibrary.org/b/isbn/9781108422161-L.jpg"),
            new BookData("Mecânica Clássica", "Goldstein", "Lagrangiano, hamiltoniano, ação, simetrias, teoria de perturbação, caos.", 2002, new BigDecimal("189.90"), "https://covers.openlibrary.org/b/isbn/9780201657029-L.jpg")
        ));

        // 4. Química - 10 livros
        createBooks(catMap.get("Química"), Arrays.asList(
            new BookData("Química Geral", "Petrucci", "Átomos, ligações, estados da matéria, termodinâmica, cinética, equilíbrio, eletroquímica.", 2017, new BigDecimal("229.90"), "https://covers.openlibrary.org/b/isbn/9780134103976-L.jpg"),
            new BookData("Química Orgânica", "Paula Bruice", "Estrutura, estereoisomeria, reações, mecanismos, síntese, espectroscopia, biomoléculas.", 2016, new BigDecimal("249.90"), "https://covers.openlibrary.org/b/isbn/9780134042282-L.jpg"),
            new BookData("Química Inorgânica", "Miessler", "Estrutura atômica, ligação, simetria, complexos, organometálicos, bioinorgânica.", 2013, new BigDecimal("199.90"), "https://covers.openlibrary.org/b/isbn/9780321811059-L.jpg"),
            new BookData("Química Analítica", "Skoog", "Equilíbrio, titulações, espectroscopia, cromatografia, eletroquímica, estatística.", 2013, new BigDecimal("199.90"), "https://covers.openlibrary.org/b/isbn/9780495558286-L.jpg"),
            new BookData("Química Física", "Atkins", "Termodinâmica, cinética, mecânica quântica, espectroscopia, química estatística.", 2014, new BigDecimal("219.90"), "https://covers.openlibrary.org/b/isbn/9780199697403-L.jpg"),
            new BookData("Bioquímica", "Lehninger", "Proteínas, enzimas, metabolismo, DNA/RNA, sinalização, membranas, técnicas.", 2021, new BigDecimal("259.90"), "https://covers.openlibrary.org/b/isbn/9781319328451-L.jpg"),
            new BookData("Química Ambiental", "Manahan", "Poluição atmosférica, aquática, solo, resíduos, toxicologia, química verde.", 2017, new BigDecimal("189.90"), "https://covers.openlibrary.org/b/isbn/9781498776936-L.jpg"),
            new BookData("Química de Polímeros", "Odian", "Polimerização, estrutura, propriedades, processamento, biodegradáveis, aplicações.", 2004, new BigDecimal("179.90"), "https://covers.openlibrary.org/b/isbn/9780471274001-L.jpg"),
            new BookData("Espectroscopia", "Pavia", "IR, UV-Vis, NMR, MS, Raman, determinação estrutural, aplicações.", 2014, new BigDecimal("199.90"), "https://covers.openlibrary.org/b/isbn/9781285460123-L.jpg"),
            new BookData("Química Medicinal", "Thomas", "Desenho de fármacos, SAR, ADMET, alvos biológicos, otimização, ensaios clínicos.", 2011, new BigDecimal("199.90"), "https://covers.openlibrary.org/b/isbn/9780470626617-L.jpg")
        ));

        // 5. Biologia - 10 livros
        createBooks(catMap.get("Biologia"), Arrays.asList(
            new BookData("Biologia Molecular da Célula", "Alberts", "Célula, DNA, expressão gênica, sinalização, ciclo celular, câncer, imunologia.", 2014, new BigDecimal("279.90"), "https://covers.openlibrary.org/b/isbn/9780815344322-L.jpg"),
            new BookData("Genética", "Griffiths", "Herança mendeliana, mapeamento, DNA, regulação gênica, genômica, genética populacional.", 2015, new BigDecimal("199.90"), "https://covers.openlibrary.org/b/isbn/9781464109461-L.jpg"),
            new BookData("Evolução", "Futuyma", "Seleção natural, deriva, especiação, filogenia, macroevolução, coevolução.", 2013, new BigDecimal("189.90"), "https://covers.openlibrary.org/b/isbn/9781605351155-L.jpg"),
            new BookData("Ecologia", "Begon", "Populações, comunidades, ecossistemas, biodiversidade, conservação, mudanças climáticas.", 2014, new BigDecimal("199.90"), "https://covers.openlibrary.org/b/isbn/9780470909133-L.jpg"),
            new BookData("Genética Molecular", "Watson", "DNA, RNA, replicação, transcrição, tradução, regulação, engenharia genética.", 2013, new BigDecimal("189.90"), "https://covers.openlibrary.org/b/isbn/9780805346350-L.jpg"),
            new BookData("Microbiologia", "Brock", "Bactérias, arqueas, vírus, metabolismo, ecologia microbiana, patogenicidade.", 2015, new BigDecimal("219.90"), "https://covers.openlibrary.org/b/isbn/9780321897398-L.jpg"),
            new BookData("Neurobiologia", "Kandel", "Neurônios, sinapses, sistemas sensoriais, motores, memória, aprendizagem, doenças.", 2013, new BigDecimal("229.90"), "https://covers.openlibrary.org/b/isbn/9780071810012-L.jpg"),
            new BookData("Biologia do Desenvolvimento", "Gilbert", "Gametogênese, fertilização, gastrulação, organogênese, células-tronco, regeneração.", 2014, new BigDecimal("219.90"), "https://covers.openlibrary.org/b/isbn/9781605351926-L.jpg"),
            new BookData("Imunologia", "Abbas", "Inata, adaptativa, anticorpos, células T, vacinas, autoimunidade, imunoterapia.", 2017, new BigDecimal("219.90"), "https://covers.openlibrary.org/b/isbn/9780323390828-L.jpg"),
            new BookData("Biologia de Sistemas", "Kitano", "Redes gênicas, modelagem, omics, simulação, medicina de precisão, biologia sintética.", 2012, new BigDecimal("189.90"), "https://covers.openlibrary.org/b/isbn/9781441998637-L.jpg")
        ));

        // 6. Medicina - 10 livros
        createBooks(catMap.get("Medicina"), Arrays.asList(
            new BookData("Anatomia Humana", "Moore", "Sistemas: esquelético, muscular, nervoso, vascular, vísceras, seções transversais.", 2017, new BigDecimal("249.90"), "https://covers.openlibrary.org/b/isbn/9780323396141-L.jpg"),
            new BookData("Fisiologia", "Guyton", "Homeostase, membrana, nervos, músculo, coração, circulação, respiração, rim, endócrino.", 2020, new BigDecimal("229.90"), "https://covers.openlibrary.org/b/isbn/9780323597128-L.jpg"),
            new BookData("Farmacologia", "Katzung", "Mecanismos, farmacocinética, farmacodinâmica, autacoides, CNS, cardiovascular, antibióticos.", 2017, new BigDecimal("219.90"), "https://covers.openlibrary.org/b/isbn/9781259641152-L.jpg"),
            new BookData("Patologia", "Robbins", "Lesão celular, inflamação, imunidade, neoplasia, genética, ambiental, sistêmica.", 2020, new BigDecimal("249.90"), "https://covers.openlibrary.org/b/isbn/9780323531139-L.jpg"),
            new BookData("Microbiologia Médica", "Murray", "Bactérias, vírus, fungos, parasitas, imunidade, vacinas, antimicrobianos, infecções.", 2019, new BigDecimal("219.90"), "https://covers.openlibrary.org/b/isbn/9780323673228-L.jpg"),
            new BookData("Imunologia Clínica", "Abbas", "Inata, adaptativa, autoimunidade, hipersensibilidade, imunodeficiência, transplante.", 2017, new BigDecimal("199.90"), "https://covers.openlibrary.org/b/isbn/9780323390828-L.jpg"),
            new BookData("Neurologia", "Bradley", "Exame neurológico, cefaleia, epilepsia, AVC, desmielinização, movimento, demência.", 2015, new BigDecimal("249.90"), "https://covers.openlibrary.org/b/isbn/9780323287821-L.jpg"),
            new BookData("Cardiologia", "Braunwald", "Insuficiência, arritmias, coronariana, valvular, hipertensão, prevenção, imagem.", 2018, new BigDecimal("299.90"), "https://covers.openlibrary.org/b/isbn/9780323444408-L.jpg"),
            new BookData("Oncologia", "DeVita", "Biologia tumoral, quimioterapia, radioterapia, imunoterapia, alvos moleculares.", 2018, new BigDecimal("299.90"), "https://covers.openlibrary.org/b/isbn/9781496389116-L.jpg"),
            new BookData("Emergências Médicas", "Tintinalli", "Trauma, choque, vias aéreas, cardíaca, neurológica, toxicológica, pediátrica.", 2020, new BigDecimal("279.90"), "https://covers.openlibrary.org/b/isbn/9781260019933-L.jpg")
        ));

        // Demais categorias - 5-10 livros cada
        seedRemainingCategories(catMap);
    }

    private void createBooks(Category category, List<BookData> books) {
        if (category == null) {
            throw new IllegalArgumentException("Categoria não encontrada para os livros informados.");
        }

        for (BookData data : books) {
            category.getBooks().add(createBook(
                data.title, data.author, data.text, data.year, data.price, data.imageUrl, category
            ));
        }
    }

    private void seedRemainingCategories(Map<String, Category> catMap) {
        List<String> seededCategories = Arrays.asList(
            "Informática", "Matemática", "Física", "Química", "Biologia", "Medicina"
        );

        for (Category category : catMap.values()) {
            if (seededCategories.contains(category.getName())) {
                continue;
            }

            for (int i = 1; i <= 5; i++) {
                String title = "Livro de " + category.getName() + " " + i;
                createBooks(category, Arrays.asList(new BookData(
                    title,
                    "Autor " + i,
                    "Livro de referência sobre " + category.getDescription() + ".",
                    2020 + i,
                    new BigDecimal("49.90").add(new BigDecimal(i * 5)),
                    getPlaceholder(title)
                )));
            }
        }
    }

    // Helper class for book data
    private static class BookData {
        String title, author, text, imageUrl;
        Integer year;
        BigDecimal price;
        BookData(String title, String author, String text, Integer year, BigDecimal price, String imageUrl) {
            this.title = title; this.author = author; this.text = text; this.year = year; this.price = price; this.imageUrl = imageUrl;
        }
    }

    private Category createCategory(String name, String description) {
        Category c = new Category();
        c.setName(name);
        c.setDescription(description);
        c.setIsActive(true);
        c.setIsDeleted(false);
        return c;
    }

    private String getPlaceholder(String title) {
        String encoded = title.replace(" ", "+").replace(":", "").replace(",", "").replace("ç", "c").replace("ã", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u").replace("à", "a").replace("â", "a").replace("ê", "e").replace("ô", "o");
        return "https://via.placeholder.com/300x450/0B3D91/FFFFFF?text=" + encoded;
    }

    private Book createBook(String title, String author, String text, Integer year, BigDecimal price, String imageUrl, Category category) {
        Book b = new Book();
        b.setTitle(title);
        b.setAuthor(author);
        b.setText(text);
        b.setYearPublication(year);
        b.setPrice(price);
        b.setImagem(imageUrl);
        b.setAtivo(true);
        b.setCategory(category);
        return b;
    }
}